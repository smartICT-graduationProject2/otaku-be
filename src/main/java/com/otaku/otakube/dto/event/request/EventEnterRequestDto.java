package com.otaku.otakube.dto.event.request;

import lombok.Getter;

@Getter
public class EventEnterRequestDto {

    private Long userId;
    private Long eventId;
    private String xNickname;
    private String xId;

    public EventEnterRequestDto(Long userId, Long eventId, String xNickname, String xId) {
        this.userId = userId;
        this.eventId = eventId;
        this.xNickname = xNickname;
        this.xId = xId;
    }
}
