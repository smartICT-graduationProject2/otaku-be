package com.otaku.otakube.dto.event.request;

import lombok.Getter;

@Getter
public class CodeInputRequestDto {

    private Long userId;
    private Long eventId;
    private Integer code;

    public CodeInputRequestDto(Long userId, Long eventId, Integer code) {
        this.userId = userId;
        this.eventId = eventId;
        this.code = code;
    }
}
