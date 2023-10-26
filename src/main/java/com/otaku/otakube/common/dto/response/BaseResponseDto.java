package com.otaku.otakube.common.dto.response;


import com.otaku.otakube.common.exception.constants.ErrorDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class BaseResponseDto<T> {
    @Schema(description = "응답 코드", defaultValue = "1000", example = "1000")
    private final int code;
    @Schema(description = "응답 상태", defaultValue = "200", example = "200")
    private final int status;
    @Schema(description = "응답 메시지", defaultValue = "요청이 성공하였습니다.", example = "요청이 성공하였습니다.")
    private final String message;

    private final LocalDateTime timestamp = LocalDateTime.now();

    //    @JsonInclude(JsonInclude.Include.NON_NULL) -> JSON key의 통일성을 위해 사용하지 않음
    @Schema(description = "응답 데이터")
    private final T data;

    private BaseResponseDto(T data) {
        this.code = ErrorDetails.SUCCESS.getCode();
        this.status = ErrorDetails.SUCCESS.getStatus();
        this.message = ErrorDetails.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * data를 통해 BaseResponseDto를 생성한다.
     *
     * @param data 클라이언트로 반환할 데이터
     * @return data를 담은 BaseResponseDto 객체
     */
    public static <T> BaseResponseDto<T> of(T data) {
        return new BaseResponseDto<>(data);
    }

    /**
     * data를 통해 해당 data를 담은 BaseResponseDto를 body로 가진 ResponseEntity를 생성한다.
     * HTTP Status Code는 200이다.
     *
     * @param data 클라이언트로 반환할 데이터
     * @return data를 담은 BaseResponseDto 객체를 body로 가진 ResponseEntity
     */
    public static <T> ResponseEntity<BaseResponseDto<T>> success(T data) {
        return ResponseEntity.ok(new BaseResponseDto<>(data));
    }

    public static <T> ResponseEntity<BaseResponseDto> success() {
        return ResponseEntity.status(200).body(new BaseResponseDto<>(null));
    }

    public static <T> ResponseEntity<BaseResponseDto> created() {
        return ResponseEntity.status(201).body(new BaseResponseDto<>(null));
    }

    public static <T> ResponseEntity<BaseResponseDto<T>> created(T data) {
        return ResponseEntity.status(201).body(new BaseResponseDto<>(data));
    }
}
