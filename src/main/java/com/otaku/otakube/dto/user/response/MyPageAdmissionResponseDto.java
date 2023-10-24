package com.otaku.otakube.dto.user.response;

import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.EventLog;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(name = "마이페이지 조회 입장권 응답 dto")
public record MyPageAdmissionResponseDto (
        @Schema(description = "이벤트 아이디")
        Long eventId,

        @Schema(description = "이벤트 제목")
        String name,

        @Schema(description = "이벤트 개최일")
        LocalDate openedDate
){
}
