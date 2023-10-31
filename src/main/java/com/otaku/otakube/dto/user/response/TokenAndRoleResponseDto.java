package com.otaku.otakube.dto.user.response;

import com.otaku.otakube.entity.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "토큰 응답값 dto")
@Builder
public record TokenAndRoleResponseDto(
        String accessToken,
        String refreshToken,
        Role role
){
}
