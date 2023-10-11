package com.otaku.otakube.common.security.consts;

public interface AuthConst {
    String AUTHORIZATION_HEADER = "Authorization";
    String BEARER_PREFIX = "Bearer ";
    String ROLE = "ROLE";
    String ROLE_USER = "ROLE_USER";
    String ROLE_ADMIN = "ROLE_ADMIN";
    String ROLE_HOST = "ROLE_HOST";

    String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/health-check",
            "/favicon.ico",
            "/auth/**",
            "/admins/view/**",
            "/css/**",
            "/"
    };
}
