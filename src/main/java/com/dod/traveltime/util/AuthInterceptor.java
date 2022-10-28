package com.dod.traveltime.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    @Value("${tt.app.key}")
    private String appKey;
    @Value("${tt.app.value}")
    private String appValue;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Aes256 aesUtil = new Aes256(appKey);
        String appAuth = request.getHeader("App_auth");

        System.out.println("appValue = " + aesUtil.encode(appValue));

        if(appAuth != null) {
            return aesUtil.decode(appAuth).equals(appValue);
        }else {
            return false;
        }
    }
}
