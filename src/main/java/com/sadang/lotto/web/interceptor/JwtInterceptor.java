package com.sadang.lotto.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sadang.lotto.exception.UnauthorizedException;
import com.sadang.lotto.service.JwtService;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	private static final String HEADER_AUTH = "Authorization";
	@Autowired
	JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) 
			throws UnauthorizedException {
		logger.info("================ Check token start");
		final String token = request.getHeader(HEADER_AUTH);
		
		if(token != null & jwtService.isUsable(token)) {
			logger.info("================ Check token end");
			return true;
		}
		else {
			logger.info("================ Check token exception :: UnauthorizedException");
			throw new UnauthorizedException();
		}
	}
	
	@Override
	public void postHandle( HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView) {
		logger.info("================ Method executed start");
		
		logger.info("================ Method executed end");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object handler, 
								Exception ex) {
		logger.info("================ Method completed start");
		
		logger.info("================ Method completed end");
	}
}
