package com.otaku.otakube.dto.subject.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "이벤트 대상 조회 응답값 dto")
public record SubjectResponseDto(
        Long subjectId,
        String name
) {
}
