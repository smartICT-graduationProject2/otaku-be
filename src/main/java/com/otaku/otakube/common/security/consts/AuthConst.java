package com.otaku.otakube.common.security.consts;

import lombok.Getter;

@Getter
public enum AuthConst {
    AUTHORIZATION_HEADER("Authorization"),
    BEARER_PREFIX("Bearer "),
    ROLE("ROLE"),
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_HOST("ROLE_HOST");

    private final String value;

    AuthConst(String value) {
        this.value = value;
    }

    public static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/health-check",
            "/favicon.ico",
            "/auth/**",
            "/admins/view/**",
            "/"
    };
}

