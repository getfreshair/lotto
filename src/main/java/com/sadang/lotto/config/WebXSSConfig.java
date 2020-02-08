package com.sadang.lotto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import com.sadang.lotto.web.filter.RequestBodyXSSFileter;

/**
 * 
 * @author 1
 *	Lucy-Xss-Servlet-Filter 라이브러리는 form-data에 대해서만 적용되고 JSON에 대해서는 처리해주지 않기 때문에 
 *	RequestBody로 넘어노는 JSON에 대한 필터 등록(두 번째 메소드)
 */
@Configuration
public class WebXSSConfig implements WebMvcConfigurer {
	@Autowired
	private RequestBodyXSSFileter requestBodyXSSFileter;
	
	// Form data
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> getXssEscapeServletFilterRegistrationBean() {
        FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<XssEscapeServletFilter>();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    // Request Body JSON
    @Bean
    public FilterRegistrationBean<RequestBodyXSSFileter> getRequestBodyXSSFileterRegistrationBean() {
        FilterRegistrationBean<RequestBodyXSSFileter> registrationBean = new FilterRegistrationBean<RequestBodyXSSFileter>();
        registrationBean.setFilter(requestBodyXSSFileter);
        registrationBean.setOrder(2);
        registrationBean.addUrlPatterns("/*"); //json으로 처리되는 url 별도 기입
        return registrationBean;
    }
}
