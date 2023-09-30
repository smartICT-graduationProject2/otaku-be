package com.otaku.otakube.common.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otaku.otakube.common.exception.constants.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorDetails errorDetails = (ErrorDetails) request.getAttribute("exception");

        if (errorDetails != null) setResponse(response, errorDetails);
    }

    private void setResponse(HttpServletResponse response, ErrorDetails error) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(error.getStatus());

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("code", error.getCode());
        responseMap.put("status", error.getStatus());
        responseMap.put("message", error.getMessage());
        responseMap.put("timestamp", LocalDateTime.now());

        String responseJson = objectMapper.writeValueAsString(responseMap);
        response.getWriter().print(responseJson);
    }
}
