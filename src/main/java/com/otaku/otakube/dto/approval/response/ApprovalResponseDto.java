package com.otaku.otakube.dto.approval.response;

import com.otaku.otakube.entity.common.ApprovalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "참여자 인증 조회 응답 dto")
public record ApprovalResponseDto(

        @Schema(description = "인증 아이디")
        @NotNull
        Long approvalId,

        @Schema(description = "참여자 트위터 닉네임")
        @NotNull
        String xNickname,

        @NotNull
        @Schema(description = "참여자 트위터 아이디")
        String xId,

        @Schema(description = "인증 상태")
        @NotNull
        ApprovalStatus status
){
}
