package com.sadang.lotto.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class HttpInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) {
		logger.info("================ Before method start");
		logger.info("================ Before method end");
		return true;
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
		for(String param : response.getHeaderNames()) {
			logger.info("response header" + param + " :: " + response.getHeader(param));
		}
		logger.info("================ Method completed end");
	}
}
