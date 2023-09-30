package com.otaku.otakube.service.user;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.exception.custom.user.UserException;
import com.otaku.otakube.common.security.jwt.JwtProvider;
import com.otaku.otakube.dto.user.request.UserLoginRequestDto;
import com.otaku.otakube.dto.user.response.TokenResponseDto;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCreateService {

    private final UserFindService userFindService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public TokenResponseDto loginUser(UserLoginRequestDto requestDto) {

        User createdUser = null;
        try{
            createdUser = userFindService.findUserByEmail(requestDto.email());
        }catch (UserException e){
            createdUser = userRepository.save(requestDto.toEntity());
        }

        return TokenResponseDto
                .builder()
                .accessToken(jwtProvider.generateAccessToken(createdUser.getUserId(), createdUser.getRole()))
                .refreshToken(jwtProvider.generateRefreshToken(createdUser.getUserId(), createdUser.getRole()))
                .build();
    }

}
