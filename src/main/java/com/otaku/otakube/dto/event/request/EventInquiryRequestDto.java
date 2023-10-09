package com.otaku.otakube.dto.event.request;

import lombok.Getter;

/**
 * 이벤트 조회 조건
 */
@Getter
public class EventInquiryRequestDto {

    private Long userId; //누구의 관심 이벤트 인지
    private Boolean isWishList; //true면 관심 이벤트, false면 오늘의 이벤트
    private String subject; //주인공 이름

    public EventInquiryRequestDto(Long userId, Boolean isWishList, String subject) {
        this.userId = userId;
        if (isWishList == null)
            this.isWishList = false;
        else
            this.isWishList = isWishList;
        this.subject = subject;
    }
}
