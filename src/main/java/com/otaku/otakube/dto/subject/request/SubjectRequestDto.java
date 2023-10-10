package com.otaku.otakube.dto.subject.request;

import com.otaku.otakube.entity.event.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "대상 등록 dto")
public record SubjectRequestDto (
        @Schema(description = "카테고리", defaultValue = "아이돌", example = "아이돌")
        @NotBlank(message = "카테고리는 필수 입력값입니다.")
        String category,

        @Schema(description = "대상 이름", defaultValue = "엑소 디오", example = "엑소 디오")
        @NotBlank(message = "대상 이름은 필수 입력값입니다.")
        String subjectName
){
    public Subject toEntity(){
        return Subject.builder()
                .category(category)
                .name(subjectName)
                .build();
    }
}
