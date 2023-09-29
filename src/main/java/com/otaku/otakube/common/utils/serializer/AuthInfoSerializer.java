package com.otaku.otakube.common.utils.serializer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.security.helper.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthInfoSerializer {

    private final ObjectMapper objectMapper;

    public String serialize(final AuthInfo authInfo) {
        try {
            return objectMapper.writeValueAsString(authInfo);
        } catch (JsonProcessingException e) {
            throw CustomException.of(ErrorDetails.JSON_PROCESSING_ERROR);
        }
    }

    public AuthInfo deserialize(final String authenticationInfoString) {
        try {
            return objectMapper.readValue(authenticationInfoString, AuthInfo.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw CustomException.of(ErrorDetails.JSON_PROCESSING_ERROR);
        }
    }
}
