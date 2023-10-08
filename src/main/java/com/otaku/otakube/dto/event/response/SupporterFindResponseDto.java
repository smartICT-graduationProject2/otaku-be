package com.otaku.otakube.dto.event.response;

import lombok.Getter;

@Getter
public class SupporterFindResponseDto {

    private String name;
    private String authUrl;
    private Long supportAmount;

    public SupporterFindResponseDto(String name, String authUrl, Long supportAmount) {
        this.name = name;
        this.authUrl = authUrl;
        this.supportAmount = supportAmount;
    }
}
