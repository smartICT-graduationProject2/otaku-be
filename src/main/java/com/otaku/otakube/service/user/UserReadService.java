package com.otaku.otakube.service.user;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.exception.custom.user.UserException;
import com.otaku.otakube.common.security.jwt.JwtProvider;
import com.otaku.otakube.dto.admin.request.AdminLoginRequestDto;
import com.otaku.otakube.dto.user.request.UserRefreshTokensRequestDto;
import com.otaku.otakube.dto.user.response.TokenResponseDto;
import com.otaku.otakube.entity.user.ActiveStatus;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReadService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public User findUserById(Long userId) {
        return userRepository.findUserByUserIdAndStatus(userId, ActiveStatus.ACTIVE).orElseThrow(
                () -> UserException.of(ErrorDetails.USER_NOT_FOUND)
        );
    }

    public User findUserByEmail(String userEmail) {
        return userRepository.findUserByEmailAndStatus(userEmail, ActiveStatus.ACTIVE).orElseThrow(
                () -> UserException.of(ErrorDetails.USER_NOT_FOUND)
        );
    }

    public TokenResponseDto refreshTokens(UserRefreshTokensRequestDto requestDto){
        String newAccessToken = jwtProvider.reIssueAccessToken(requestDto.refreshToken());
        String newRefreshToken = jwtProvider.reIssueRefreshToken(requestDto.refreshToken());

        return TokenResponseDto
                .builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

    }

    public String loginAdmin(AdminLoginRequestDto dto) {

        if (!dto.password().equals("pwdadmin")) throw UserException.of(ErrorDetails.UNAUTHORIZED);

        User admin = userRepository.findById(1L)
                .orElseThrow( () -> CustomException.of(ErrorDetails.INTERNAL_SERVER_ERROR));

        return jwtProvider.generateAccessToken(admin.getUserId(), admin.getRole());
    }
}
