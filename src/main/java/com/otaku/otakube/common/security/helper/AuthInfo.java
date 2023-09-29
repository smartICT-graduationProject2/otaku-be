package com.otaku.otakube.common.security.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.otaku.otakube.entity.user.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthInfo {
    private Long id;
    private Role role;

    @JsonCreator
    public AuthInfo(@JsonProperty("id") Long id, @JsonProperty("role") Role role) {
        this.id = id;
        this.role = role;
    }
}
