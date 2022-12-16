package com.jolt.stompy.middleware;

import com.jolt.stompy.shared.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Auth filter");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = ( HttpServletResponse) servletResponse;

        String token = httpRequest.getHeader("x-auth-token");

        if(token == null) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token missing");
            return;
        }

        try {
            byte[] apiKeyParsed = DatatypeConverter.parseBase64Binary(Constants.API_SECRET_KEY);
            Claims claims = Jwts.parser()
                    .setSigningKey(apiKeyParsed)
                    .parseClaimsJws(token)
                    .getBody();

            httpRequest.setAttribute("userId", (Integer) claims.get("userId"));
            httpRequest.setAttribute("roleId", (Boolean) claims.get("roleId"));
        } catch (Exception ex) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid token");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
