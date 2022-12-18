package com.jolt.stompy.shared;

import com.jolt.stompy.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class Authorization {
    public static String generateJwt(User user) {
        long timestamp = System.currentTimeMillis();
        byte[] apiKeyParsed = DatatypeConverter.parseBase64Binary(Constants.API_SECRET_KEY);
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, apiKeyParsed)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .claim("role", user.getRole().getName())
                .compact();
    }
}
