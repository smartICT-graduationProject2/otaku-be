package com.otaku.otakube.dto.event.response;

import com.otaku.otakube.entity.common.ApprovalStatus;
import lombok.Getter;

@Getter
public class AuthenticationFindResponseDto {

    private Long authenticationId;
    private String name;
    private String xNickname;
    private ApprovalStatus status;

    public AuthenticationFindResponseDto(Long authenticationId, String name, String xNickname, ApprovalStatus status) {
        this.authenticationId = authenticationId;
        this.name = name;
        this.xNickname = xNickname;
        this.status = status;
    }
}
