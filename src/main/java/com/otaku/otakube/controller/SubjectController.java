package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.subject.request.SubjectRequestDto;
import com.otaku.otakube.dto.subject.response.SubjectResponseDto;
import com.otaku.otakube.service.subject.SubjectCreateService;
import com.otaku.otakube.service.subject.SubjectReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subject", description = "이벤트 대상 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/subjects")
@Validated
public class SubjectController {
    private final SubjectReadService subjectReadService;
    private final SubjectCreateService subjectCreateService;

    @Operation(summary = "이벤트 대상 조회 API", description = "이벤트 대상 조회 API입니다.")
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
    public ResponseEntity<BaseResponseDto<Slice<SubjectResponseDto>>> getSubjectList(
            @ParameterObject @PageableDefault(size = 12) Pageable pageable,
            @Parameter @RequestParam String category,
            @Parameter @RequestParam(required = true) Long lastSubjectId) {
        return BaseResponseDto.success(subjectReadService.getSubjectListByCategory(pageable, category, lastSubjectId));
    }

    @Operation(summary = "이벤트 대상 전체 조회 API", description = "이벤트 대상 전체 조회 API입니다.")
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
    @GetMapping("/all-list")
    public ResponseEntity<BaseResponseDto<List<SubjectResponseDto>>> getAllSubjectList() {
        return BaseResponseDto.success(subjectReadService.getAllSubjectList());
    }

    @Operation(summary = "이벤트 대상 등록 API", description = "이벤트 대상 등록 API입니다.")
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
    @PostMapping
    public ResponseEntity<BaseResponseDto> registerSubject(
            @Valid @RequestBody final SubjectRequestDto dto) {
        subjectCreateService.createSubject(dto);
        return BaseResponseDto.created();
    }

}
