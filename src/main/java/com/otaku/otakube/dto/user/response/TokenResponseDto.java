package com.otaku.otakube.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "토큰 응답값 dto")
@Builder
public record TokenResponseDto (
        String accessToken,
        String refreshToken
){
}
