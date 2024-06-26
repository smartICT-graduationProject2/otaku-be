package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.event.request.EventSaveRequestDto;
import com.otaku.otakube.dto.event.response.*;
import com.otaku.otakube.service.event.EventCreateService;
import com.otaku.otakube.service.event.EventReadService;
import com.otaku.otakube.service.report.ReportCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventCreateService eventCreateService;
    private final EventReadService eventReadService;
    private final ReportCreateService reportCreateService;

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
    @PreAuthorize("hasRole('ROLE_HOST')")
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

    @Operation(summary = "대상 조회 후 이벤트 조회 API", description = "대상 조회 후 이벤트 조회 API입니다.")
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
    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponseDto<Slice<EventListResponseDto>>> getEventListBySubjectId(
            @ParameterObject @PageableDefault(size = 12) Pageable pageable,
            @Parameter @RequestParam(name = "subjectId") final Long subjectId) {
        return BaseResponseDto.success(eventReadService.findEventListBySubjectId(pageable, subjectId));
    }

    @Operation(summary = "이벤트 조회 API", description = "이벤트 조회 API입니다.")
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
    @GetMapping
    public ResponseEntity<BaseResponseDto<Slice<EventSearchResponseDto>>> getEventListByConditions(
            @ParameterObject @PageableDefault(size = 12) Pageable pageable,
            @Parameter @RequestParam(name = "subject", required = false) final String subject,
            @Parameter @RequestParam(name = "is-wish-list", defaultValue = "false") final boolean isWishList) {
        return BaseResponseDto.success(eventReadService.findEventList(pageable, isWishList, subject));
    }

    @Operation(summary = "개최한 이벤트 조회 API", description = "개최한 이벤트 조회 API 입니다.")
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
    @PreAuthorize("hasRole('ROLE_HOST')")
    @GetMapping("/host")
    public ResponseEntity<BaseResponseDto<Slice<EventHostResponseDto>>> getEventListByHost(
            @ParameterObject @PageableDefault(size = 12) Pageable pageable) {
        return BaseResponseDto.success(eventReadService.findEventByHost(pageable));
    }

    @Operation(summary = "이벤트 입장권 조회 API", description = "이벤트 입장권 조회 API 입니다.")
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
    @GetMapping("/admission-ticket/{eventId}")
    public ResponseEntity<BaseResponseDto<EventAdmissionResponseDto>> getEventAdmission(
            @ParameterObject @PathVariable(name = "eventId") final Long eventId) {
        return BaseResponseDto.success(eventReadService.findEventAdmission(eventId));
    }

    @Operation(summary = "이벤트 특전 조회 API", description = "이벤트 특전 조회 API 입니다.")
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
    @GetMapping("/perks-image/{eventId}")
    public ResponseEntity<BaseResponseDto<EventPerkResponseDto>> getEventPerks(
            @ParameterObject @PathVariable(name = "eventId") final Long eventId) {
        return BaseResponseDto.success(eventReadService.findEventPerk(eventId));
    }


    @Operation(summary = "이벤트 상세 조회 API", description = "이벤트 상세 조회 API입니다.")
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
    @GetMapping("/{eventId}")
    public ResponseEntity<BaseResponseDto<EventDetailResponseDto>> getEventDetailInfo(
            @ParameterObject @PathVariable(name = "eventId") final Long eventId) {
        return BaseResponseDto.success(eventReadService.findEventDetailInfo(eventId));
    }

    @Operation(summary = "이벤트 신고 API", description = "이벤트 신고 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "신고 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @PostMapping("/report/{eventId}")
    public ResponseEntity<BaseResponseDto> reportEvent(
            @ParameterObject @PathVariable(name = "eventId") final Long eventId) {
        reportCreateService.createReport(eventId);
        return BaseResponseDto.created();
    }
}
