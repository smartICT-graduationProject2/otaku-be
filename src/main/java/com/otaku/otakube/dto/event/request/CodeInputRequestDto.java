package com.otaku.otakube.dto.event.request;

import lombok.Getter;

@Getter
public class CodeInputRequestDto {

    private Long eventId;
    private Integer code;

    public CodeInputRequestDto(Long eventId, Integer code) {
        this.eventId = eventId;
        this.code = code;
    }
}
