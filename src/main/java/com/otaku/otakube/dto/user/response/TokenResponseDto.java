package com.otaku.otakube.dto.user.response;

import lombok.Builder;

@Builder
public record TokenResponseDto (
        String accessToken,
        String refreshToken
){
}
