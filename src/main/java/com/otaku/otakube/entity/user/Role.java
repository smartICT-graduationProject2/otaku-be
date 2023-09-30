package com.otaku.otakube.entity.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Stream;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_HOST, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    @JsonCreator
    public static Role parsing(String inputValue) {
        return Stream.of(Role.values())
                .filter(role -> role.name().equals(inputValue))
                .findAny()
                .orElseThrow(() -> CustomException.of(ErrorDetails.INVALID_INPUT_ENUM));

    }

    @JsonValue
    public String toValue() {
        return this.name();
    }

}
