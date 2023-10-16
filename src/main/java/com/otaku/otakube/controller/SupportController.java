package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.support.request.SupportRequestDto;
import com.otaku.otakube.service.support.SupportCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Support", description = "이벤트 대상 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/supports")
@Validated
public class SupportController {

    private final SupportCreateService supportCreateService;

    @Operation(summary = "후원 등록 API", description = "후원 등록 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "생성 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<BaseResponseDto> registerSubject(
            @ParameterObject @RequestParam(name = "eventId") final Long eventId,
            @Valid @RequestBody final SupportRequestDto dto) {
        supportCreateService.createSupport(dto, eventId);
        return BaseResponseDto.created();
    }

}
