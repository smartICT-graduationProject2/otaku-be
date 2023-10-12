package com.otaku.otakube.dto.event.response;

import java.time.LocalDate;

public record EventDetailInfoResponseDto (
        Boolean isPublic,
        String xNickname,
        String xId,
        String eventName, //이벤트 이름
        String subjectName, //대상 아이디
        String category, //카테고리
        LocalDate openedDate,
        LocalDate closedDate,
        String address,
        String description
){
}
