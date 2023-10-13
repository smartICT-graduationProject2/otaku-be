package com.otaku.otakube.dto.event.request;

import com.otaku.otakube.entity.event.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


/**
 * 이벤트 등록 Request
 */
public record EventSaveRequestDto(

        @Schema(description = "이벤트 공개 여부")
        @NotNull
        Boolean isPublic,
        @Schema(description = "트위터 닉네임")
        @Size(min = 1, max = 100)
        String xNickname,
        @Schema(description = "트위터 아이디")
        @Size(min = 1, max = 100)
        String xId,
        @Schema(description = "이벤트 이름")
        @Size(min = 1, max = 100)
        String name,
        @Schema(description = "이벤트 대상 아이디")
        @NotNull
        Long subjectId,
        @Schema(description = "이벤트 시작일")
        @NotNull
        LocalDate openedDate,
        @Schema(description = "이벤트 마감일")
        @NotNull
        LocalDate closedDate,
        @Schema(description = "이벤트 장소 주소")
        @Size(min = 1, max = 255)
        String address,
        @Schema(description = "이벤트 설명")
        @Size(min = 1, max = 500)
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
