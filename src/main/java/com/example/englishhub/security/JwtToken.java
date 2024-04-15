package com.example.englishhub.security;

/**
 * @Author: hahaha
 * @Date: 2024/4/11 17:26
 */

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 继承AuthenticationToken，跟JwtRealmh中的doGetAuthenticationInfo的参数类型保持一致
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token){
        this.token = token;
    }


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
