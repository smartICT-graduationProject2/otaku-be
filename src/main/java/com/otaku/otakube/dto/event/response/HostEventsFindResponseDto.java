package com.otaku.otakube.dto.event.response;

import lombok.Getter;

@Getter
public class HostEventsFindResponseDto {

    private String name;
    private String xNickname;
    private String xId;
    private String subject;
    private Integer code;

    public HostEventsFindResponseDto(String name, String xNickname, String xId, String subject, Integer code) {
        this.name = name;
        this.xNickname = xNickname;
        this.xId = xId;
        this.subject = subject;
        this.code = code;
    }
}
