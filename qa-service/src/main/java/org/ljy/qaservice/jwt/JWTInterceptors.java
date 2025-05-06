package org.ljy.qaservice.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.ljy.common.util.jwt.preHandleFunc;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class JWTInterceptors implements HandlerInterceptor {
    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return preHandleFunc.preHandle(request, response, handler);
    }
}