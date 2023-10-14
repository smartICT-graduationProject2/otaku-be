package com.otaku.otakube.common.security.jwt;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.security.consts.AuthConst;
import com.otaku.otakube.common.security.helper.AuthInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AntPathMatcher antPathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = extractTokenFromHeader(request.getHeader(AuthConst.AUTHORIZATION_HEADER.getValue()));
        jwtProvider.validateToken(token);
        setAuthentication(token);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return Arrays.stream(AuthConst.AUTH_WHITELIST).anyMatch(whitelist -> antPathMatcher.match(whitelist, request.getRequestURI()));
    }

    private String extractTokenFromHeader(final String header) {
        return Optional.ofNullable(header)
                .filter(headerValue -> headerValue.startsWith(AuthConst.BEARER_PREFIX.getValue()))
                .map(headerValue -> headerValue.replace(AuthConst.BEARER_PREFIX.getValue(), ""))
                .orElseThrow(() -> CustomException.of(ErrorDetails.INVALID_TOKEN));
    }

    private void setAuthentication(final String accessToken) {
        final AuthInfo authInfo = jwtProvider.extractAuthenticationInfoFromToken(accessToken);
        JwtAuthenticationToken jwtAuthenticationToken = JwtAuthenticationToken.of(authInfo);
        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
    }
}
