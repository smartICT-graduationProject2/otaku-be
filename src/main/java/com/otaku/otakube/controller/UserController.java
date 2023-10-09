package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.service.user.UserUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User", description = "유저 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserUpdateService userUpdateService;

    //TODO : 회원 탈퇴
    @Operation(summary = "유저 회원 탈퇴 API", description = "유저 회원 탈퇴 API입니다.")
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
    @PostMapping("/withdrawal")
    public ResponseEntity<BaseResponseDto<String>> withdrawUser() {
        userUpdateService.withdrawUser();
        return BaseResponseDto.success("success");
    }

    //TODO : 개최자 인증
    @Operation(summary = "유저 회원 탈퇴 API", description = "유저 회원 탈퇴 API입니다.")
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
    @PostMapping(value = "/host-application", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDto<String>> uploadFile(
            @Parameter(
                    description = "multipart/form-data 형식의 단일 이미지를 입력 값으로 받습니다. 이때 key 값은 image입니다.",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestPart("image") final MultipartFile multipartFile
    ) {
        userUpdateService.applyHost(multipartFile);
        return BaseResponseDto.success("success");
    }

    //TODO : 개최자 승인

}
