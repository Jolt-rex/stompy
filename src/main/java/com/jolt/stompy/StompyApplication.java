package com.jolt.stompy;

import com.jolt.stompy.middleware.AdminFilter;
import com.jolt.stompy.middleware.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StompyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StompyApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> authFilterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/users/changePassword");
		registrationBean.addUrlPatterns("/api/users/me");
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<AdminFilter> adminFilterRegistrationBean() {
		FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
		AdminFilter adminFilter = new AdminFilter();
		registrationBean.setFilter(adminFilter);
		registrationBean.addUrlPatterns("/api/users");
		return registrationBean;
	}
}
