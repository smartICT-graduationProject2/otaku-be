package com.otaku.otakube.dto.event.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyPageEventsResponseDto {

    private Long eventId;
    private String name;
    private LocalDateTime createdAt;

    public MyPageEventsResponseDto(Long eventId, String name, LocalDateTime createdAt) {
        this.eventId = eventId;
        this.name = name;
        this.createdAt = createdAt;
    }
}
