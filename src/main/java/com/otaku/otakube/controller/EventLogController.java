package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.service.event.WishListCreateService;
import com.otaku.otakube.service.event.WishListDeleteService;
import com.otaku.otakube.service.eventlog.EventLogCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "EventLog", description = "이벤트 로그 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/event-logs")
@Validated
public class EventLogController {

    private final EventLogCreateService eventLogCreateService;

    @Operation(summary = "공개 이벤트 참여하기 API", description = "공개 이벤트 참여하기 API입니다.")
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
    @PostMapping("/expected")
    public ResponseEntity<BaseResponseDto> enrollPublicEvent(
            @Parameter @RequestParam final Long eventId) {
        eventLogCreateService.CreateExpectedEventLog(eventId);
        return BaseResponseDto.created();
    }
}
