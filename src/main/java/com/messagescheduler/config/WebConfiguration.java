package com.messagescheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.messagescheduler.interceptors.ClientInceptors;

@Component
public class WebConfiguration implements WebMvcConfigurer{
	@Autowired
	ClientInceptors clientInterceptors;
	
	 @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	       registry.addInterceptor(clientInterceptors).addPathPatterns("/schedule/message");
	    }
}
