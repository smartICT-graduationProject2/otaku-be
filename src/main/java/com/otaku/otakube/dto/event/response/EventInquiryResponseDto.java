package com.otaku.otakube.dto.event.response;

import com.otaku.otakube.entity.event.SupportStatus;
import lombok.Data;

/**
 * 이벤트 조회 결과
 */

@Data
public class EventInquiryResponseDto {

    private Long eventId;
    private String name;
    private String nickname;
    private String id;
    private String subject;
    private Long currentAmount;
    private SupportStatus state;
    private Boolean isWishList;


    public EventInquiryResponseDto() {
    }

    public EventInquiryResponseDto(Long eventId, String name, String nickname, String id, String subject, Long currentAmount, SupportStatus state) {
        this.eventId = eventId;
        this.name = name;
        this.nickname = nickname;
        this.id = id;
        this.subject = subject;
        this.currentAmount = currentAmount;
        this.state = state;
        this.isWishList = false;
    }
}
