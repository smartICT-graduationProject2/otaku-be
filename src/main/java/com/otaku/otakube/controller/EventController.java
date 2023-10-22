package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.event.request.EventSaveRequestDto;
import com.otaku.otakube.dto.event.response.EventSaveResponseDto;
import com.otaku.otakube.service.event.EventCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventCreateService eventCreateService;

    @Operation(summary = "이벤트 등록 API", description = "이벤트 등록 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "등록 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    //이벤트 등록
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponseDto<EventSaveResponseDto>> saveEvent(
            @Parameter(description = "multipart/form-data 형식의 단일 이미지를 입력 값으로 받습니다.")
            @RequestPart("featuredImageFile") final MultipartFile featuredImageFile,
            @Parameter(description = "multipart/form-data 형식의 단일 이미지를 입력 값으로 받습니다.")
            @RequestPart("perksImageFile") final MultipartFile perksImageFile,
            @RequestPart EventSaveRequestDto request) {
        return BaseResponseDto.created(eventCreateService.saveEvent(request, perksImageFile, featuredImageFile));
    }
}
