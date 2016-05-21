package com.cubestacklabs.restendpointtimeprofiler.logs;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestLoggerHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(RequestLoggerHandlerInterceptor.class);

    private static final String USER_ID = "APP-USER-ID";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";// this could be an encoded string
    private static final String START_TIME = "START_TIME";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if( ! (handler instanceof HandlerMethod)) {
            return true;
        }
        request.setAttribute(START_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        if( ! (handler instanceof HandlerMethod)) {
            return;
        }
        RequestLog log = log(request, response, (HandlerMethod) handler);
        //logger.info(log);
        System.out.println(log);
        System.out.println(log.toJson());
    }

    private RequestLog log(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
        return  new RequestLog.Builder()
                .clazz(handlerMethod.getBean().getClass().getName())
                .contentSize(Long.parseLong(response.getHeader("Content-Length")))
                .httpMethod(request.getMethod())
                .method(handlerMethod.getMethod().getName())
                .processingTime(System.currentTimeMillis() - (Long) request.getAttribute(START_TIME))
                .uri(request.getRequestURI())
                .userId(request.getHeader(USER_ID))
                .build();
    }
}
