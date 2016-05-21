package com.cubestacklabs.restendpointtimeprofiler;

import com.cubestacklabs.restendpointtimeprofiler.logs.RequestLoggerHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.cubestacklabs.arrow")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestLoggerHandlerInterceptor requestLoggerHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggerHandlerInterceptor);
    }
}
