package com.sadang.lotto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sadang.lotto.web.interceptor.HttpInterceptor;
import com.sadang.lotto.web.interceptor.JwtInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private static final String[] HTTP_PATH_PATTERNS = {
			"/**"
	};
	private static final String[] HTTP_EXCLUDE_PATHS = {
			
	};
	private static final String[] JWT_PATH_PATTERNS = {
			
	};
	private static final String[] JWT_EXCLUDE_PATHS = {
			"/**"
	};
	@Autowired
	private HttpInterceptor httpInterceptor;
	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns(JWT_PATH_PATTERNS)
				.excludePathPatterns(JWT_EXCLUDE_PATHS);
		
		registry.addInterceptor(httpInterceptor)
				.addPathPatterns(HTTP_PATH_PATTERNS)
				.excludePathPatterns(HTTP_EXCLUDE_PATHS);
	}
}