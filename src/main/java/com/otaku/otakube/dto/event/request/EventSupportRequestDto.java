package com.otaku.otakube.dto.event.request;

import lombok.Getter;

@Getter
public class EventSupportRequestDto {

    private Long eventId;
    private Long supporterId;
    private String authUrl;
    private Long supportAmount;

    public EventSupportRequestDto(Long eventId, Long supporterId, String authUrl, Long supportAmount) {
        this.eventId = eventId;
        this.supporterId = supporterId;
        this.authUrl = authUrl;
        this.supportAmount = supportAmount;
    }
}
