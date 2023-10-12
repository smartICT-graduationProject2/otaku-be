package com.otaku.otakube.dto.event.request;

import com.otaku.otakube.entity.event.Event;

import java.time.LocalDate;

/**
 * 이벤트 등록 Request
 */
public record EventSaveRequestDto(
    Boolean isPublic,
    String xNickname,
    String xId,
    String name, //이벤트 이름
    Long subjectId, //대상 아이디
    LocalDate openedDate,
    LocalDate closedDate,
    String address,
    String description
){
    public Event toEntity(){
        return Event.builder()
                .isPublic(isPublic)
                .xNickname(xNickname)
                .xId(xId)
                .name(name)
                .openedDate(openedDate)
                .closedDate(closedDate)
                .address(address)
                .description(description)
                .build();
    }
}
