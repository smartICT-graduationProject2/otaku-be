package com.otaku.otakube.service.user;

import com.otaku.otakube.common.security.jwt.JwtProvider;
import com.otaku.otakube.dto.user.request.UserRefreshTokensRequestDto;
import com.otaku.otakube.dto.user.response.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReadService {

    private final JwtProvider jwtProvider;

    public TokenResponseDto refreshTokens(UserRefreshTokensRequestDto requestDto){
        String newAccessToken = jwtProvider.reIssueAccessToken(requestDto.refreshToken());
        String newRefreshToken = jwtProvider.reIssueRefreshToken(requestDto.refreshToken());

        return TokenResponseDto
                .builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

    }
}
