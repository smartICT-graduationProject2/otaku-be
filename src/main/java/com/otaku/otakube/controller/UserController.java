package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.hostInspection.response.HostInspectionResponseDto;
import com.otaku.otakube.service.user.HostInspectionReadService;
import com.otaku.otakube.service.user.HostInspectionUpdateService;
import com.otaku.otakube.service.user.UserUpdateService;
import com.otaku.otakube.dto.user.response.MyPageResponseDto;
import com.otaku.otakube.service.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "User", description = "유저 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserUpdateService userUpdateService;
    private final HostInspectionReadService hostInspectionReadService;
    private final HostInspectionUpdateService hostInspectionUpdateService;
    private final MyPageReadService myPageReadService;

    @Operation(summary = "유저 회원 탈퇴 API", description = "유저 회원 탈퇴 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "회원 탈퇴 성공",
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


    @Operation(summary = "유저 개최자 인증 API", description = "유저 개최자 인증 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "개최자 인증 요청 성공",
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


    @GetMapping("/inspection-list")
    public ResponseEntity<BaseResponseDto<List<HostInspectionResponseDto>>> getHostInspectionTable() {
        return BaseResponseDto.success(hostInspectionReadService.getHostInspectionTable());
    }

    @PutMapping("/inspection/{hostInspectionId}")
    public ResponseEntity<BaseResponseDto> updateHostInspection(
            @ParameterObject @RequestParam(name = "inspectionResult") final Boolean inspectionResult,
            @PathVariable final Long hostInspectionId
    ) {
        hostInspectionUpdateService.updateHostInspection(hostInspectionId, inspectionResult);
        return BaseResponseDto.success();
    }

    @Operation(summary = "마이페이지 조회 입장권 API", description = "마이페이지 조회 입장권 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @GetMapping("/admission-tickets")
    public ResponseEntity<BaseResponseDto<List<MyPageResponseDto>>> getMyPageAdmission() {
        return BaseResponseDto.success(myPageReadService.findUserAdmission());
    }

    @Operation(summary = "마이페이지 조회 특전 API", description = "마이페이지 조회 특전 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @GetMapping("/perks-image")
    public ResponseEntity<BaseResponseDto<List<MyPageResponseDto>>> getMyPagePerks() {
        return BaseResponseDto.success(myPageReadService.findUserPerks());
    }
}
