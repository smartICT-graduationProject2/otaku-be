package com.otaku.otakube.dto.event.request;

import lombok.Getter;

@Getter
public class WishListRequestDto {

    private Long userId;
    private Long eventId;

    public WishListRequestDto(Long userId, Long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
