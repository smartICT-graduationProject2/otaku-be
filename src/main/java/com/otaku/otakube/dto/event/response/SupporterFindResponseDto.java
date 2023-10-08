package com.otaku.otakube.dto.event.response;

import com.otaku.otakube.entity.common.ApprovalStatus;
import lombok.Getter;

@Getter
public class SupporterFindResponseDto {

    private Long supportLogId;
    private String name;
    private String authUrl;
    private Long supportAmount;
    private ApprovalStatus status;

    public SupporterFindResponseDto(Long supportLogId, String name, String authUrl, Long supportAmount, ApprovalStatus status) {
        this.supportLogId = supportLogId;
        this.name = name;
        this.authUrl = authUrl;
        this.supportAmount = supportAmount;
        this.status = status;
    }
}
