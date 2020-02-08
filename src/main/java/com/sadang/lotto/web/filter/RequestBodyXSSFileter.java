package com.sadang.lotto.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sadang.lotto.web.wrapper.RequestWrapper;

@Component
@Order(2)
public class RequestBodyXSSFileter implements Filter{
	private Logger logger = LoggerFactory.getLogger(RequestBodyXSSFileter.class);
	
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        RequestWrapper requestWrapper = null;
        Enumeration<String> param = request.getHeaderNames();
        String header;
        try{
        	while(param.hasMoreElements()) {
        		header = param.nextElement();
        		System.out.println("response header" + header + " :: " + request.getHeader(header));
        	}
            requestWrapper = new RequestWrapper(request);
        }catch(Exception e){
        	logger.error(e.getMessage(), e);
        }

        chain.doFilter(requestWrapper, response);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
