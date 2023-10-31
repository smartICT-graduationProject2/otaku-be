package com.otaku.otakube.dto.approval.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "참여자 인증 등록 dto")
public record ApprovalRequestDto(

        @Schema(description = "참여자 트위터 닉네임")
        @NotNull
        String xNickname,

        @NotNull
        @Schema(description = "참여자 트위터 아이디")
        String xId
){
}
