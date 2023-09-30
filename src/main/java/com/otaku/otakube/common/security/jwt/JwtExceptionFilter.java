package com.otaku.otakube.common.security.jwt;


import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.security.consts.AuthConst;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    private final AntPathMatcher antPathMatcher;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            request.setAttribute("exception", e.getResponseDetails());
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return Arrays.stream(AuthConst.AUTH_WHITELIST).anyMatch(whitelist -> antPathMatcher.match(whitelist, request.getRequestURI()));
    }

}
