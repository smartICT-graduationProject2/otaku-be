package com.otaku.otakube.controller;


import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.dto.approval.response.ApprovalResponseDto;
import com.otaku.otakube.service.approval.ApprovalReadService;
import com.otaku.otakube.service.approval.ApprovalUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Approval", description = "개최자 인증 관련 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/approvals")
public class ApprovalController {

    private final ApprovalReadService approvalReadService;
    private final ApprovalUpdateService approvalUpdateService;

    @Operation(summary = "개최자의 참여자 조회 API", description = "개최자의 참여자 조회 API입니다.")
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
    public ResponseEntity<BaseResponseDto<List<ApprovalResponseDto>>> findEventApprovalList(
            @Parameter @RequestParam final Long eventId) {
        return BaseResponseDto.success(approvalReadService.findApprovalByEvent(eventId));
    }

    @Operation(summary = "개최자의 참여자 승인 API", description = "개최자의 참여자 승인 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "승인 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = BaseErrorResponseDto.class))
                    )
            }
    )
    @PutMapping("/{approvalId}")
    public ResponseEntity<BaseResponseDto> approveUser(
            @ParameterObject @PathVariable(name = "approvalId") final Long approvalId) {
        approvalUpdateService.updateApprovalStatus(approvalId);
        return BaseResponseDto.success();
    }


}
