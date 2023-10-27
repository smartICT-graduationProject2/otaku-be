package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.approval.request.ApprovalRequestDto;
import com.otaku.otakube.dto.event.response.EventPerkResponseDto;
import com.otaku.otakube.service.eventlog.EventLogCreateService;
import com.otaku.otakube.service.eventlog.EventLogUpdateService;
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
    private final EventLogUpdateService eventLogUpdateService;

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
        eventLogCreateService.createExpectedEventLog(eventId);
        return BaseResponseDto.created();
    }

    @Operation(summary = "비공개 이벤트 참여하기 API", description = "비공개 이벤트 참여하기 API입니다.")
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
    @PostMapping("/pre-auth")
    public ResponseEntity<BaseResponseDto> enrollPrivateEvent(
            @Parameter @RequestParam final Long eventId,
            @RequestPart ApprovalRequestDto request) {
        eventLogCreateService.createPreAuthEventLog(eventId, request);
        return BaseResponseDto.created();
    }

    @Operation(summary = "이벤트 코드 입력 API", description = "이벤트 코드 입력 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "코드 입력 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @PostMapping("/code")
    public ResponseEntity<BaseResponseDto<EventPerkResponseDto>> registerEventCode(
            @Parameter @RequestParam final Long eventId,
            @Parameter @RequestParam final Integer code) {
        return BaseResponseDto.success(eventLogUpdateService.updateEventLogStatus(eventId, code));
    }

}
