package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.admin.request.AdminLoginRequestDto;
import com.otaku.otakube.dto.user.request.UserLoginRequestDto;
import com.otaku.otakube.dto.user.request.UserRefreshTokensRequestDto;
import com.otaku.otakube.dto.user.response.TokenAndRoleResponseDto;
import com.otaku.otakube.dto.user.response.TokenResponseDto;
import com.otaku.otakube.service.user.UserCreateService;
import com.otaku.otakube.service.user.UserReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final UserCreateService userCreateService;
    private final UserReadService userReadService;

    @Operation(summary = "유저 회원가입 및 로그인 API", description = "유저 회원가입 및 로그인 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "로그인 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @PostMapping("/users/sign-up")
    public ResponseEntity<BaseResponseDto<TokenAndRoleResponseDto>> loginUser(@Valid @RequestBody final UserLoginRequestDto requestDto) {
        return BaseResponseDto.created(userCreateService.loginUser(requestDto));
    }

    @Operation(summary = "유저 토큰 재발급 API", description = "유저 토큰 재발급 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "토큰 재발급 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponseDto<TokenResponseDto>> refreshTokens(@Valid @RequestBody final UserRefreshTokensRequestDto requestDto) {
        return BaseResponseDto.created(userReadService.refreshTokens(requestDto));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> loginAdmin(AdminLoginRequestDto dto) {
        return ResponseEntity.ok(userReadService.loginAdmin(dto));
    }

}
