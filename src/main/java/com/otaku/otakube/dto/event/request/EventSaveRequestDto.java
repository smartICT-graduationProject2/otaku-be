package com.otaku.otakube.dto.event.request;

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
    String category,
    LocalDate openedDate,
    LocalDate closedDate,
    String address,
    String description
){
}
