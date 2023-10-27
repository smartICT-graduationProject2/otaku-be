package com.otaku.otakube.controller;

import com.otaku.otakube.common.dto.response.BaseErrorResponseDto;
import com.otaku.otakube.common.dto.response.BaseResponseDto;
import com.otaku.otakube.service.event.WishListCreateService;
import com.otaku.otakube.service.event.WishListDeleteService;
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

@Tag(name = "WishList", description = "관심 이벤트 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/wish-list")
@Validated
public class WishListController {

    private final WishListCreateService wishListCreateService;
    private final WishListDeleteService wishListDeleteService;

    @Operation(summary = "관심 이벤트 등록 API", description = "관심 이벤트 등록 API입니다.")
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
    @PostMapping("/enroll")
    public ResponseEntity<BaseResponseDto> enrollEventToWishList(
            @Parameter @RequestParam final Long eventId) {
        wishListCreateService.CreateWishList(eventId);
        return BaseResponseDto.created();
    }

    @Operation(summary = "관심 이벤트 해제 API", description = "사용자가 특정 이벤트에 대해 후원하는 API입니다.")
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
    @DeleteMapping(value = "/cancel")
    public ResponseEntity<BaseResponseDto> deleteWishList(
            @Parameter @RequestParam final Long eventId) {
        wishListDeleteService.DeleteWishList(eventId);
        return BaseResponseDto.created();
    }
}
