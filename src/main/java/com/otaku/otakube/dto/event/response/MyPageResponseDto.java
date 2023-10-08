package com.otaku.otakube.dto.event.response;

import lombok.Getter;

import java.util.List;

@Getter
public class MyPageResponseDto {

    private String name;
    private List<MyPageEventsResponseDto> events;
    private List<MyPageEventsResponseDto> perks;
    private Boolean isHost;

    public MyPageResponseDto(String name, List<MyPageEventsResponseDto> events, List<MyPageEventsResponseDto> perks, Boolean isHost) {
        this.name = name;
        this.events = events;
        this.perks = perks;
        this.isHost = isHost;
    }
}
