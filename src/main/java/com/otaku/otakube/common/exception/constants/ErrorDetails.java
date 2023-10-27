package com.otaku.otakube.common.exception.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ErrorDetails implements ResponseDetails {


    /**
     * 1000: 요청 성공
     */
    SUCCESS(1000, HttpStatus.OK.value(), "요청이 성공하였습니다."),
    CREATED(1001, HttpStatus.CREATED.value(), "요청이 성공하였습니다."),

    /**
     * 2000: Request 오류
     */
    BAD_REQUEST(2000, HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),
    NOT_FOUND(2001, HttpStatus.NOT_FOUND.value(), "요청한 URI를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(2002, HttpStatus.METHOD_NOT_ALLOWED.value(), "해당 URI에서 지원하지 않는 HTTP Method입니다."),
    UNSUPPORTED_MEDIA_TYPE(2003, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "지원하지 않는 Media Type입니다."),
    TOO_MANY_REQUESTS(2004, HttpStatus.TOO_MANY_REQUESTS.value(), "요청 횟수가 너무 많습니다. 잠시 후 다시 시도해주세요."),
    INVALID_INPUT_PARAMETER(2005, HttpStatus.BAD_REQUEST.value(), "API Validation에 실패했습니다. Parameter를 확인해주세요."),
    INVALID_INPUT_BODY(2006, HttpStatus.BAD_REQUEST.value(), "API Validation에 실패했습니다. Body의 형식이 일치하는 지 확인해주세요."),
    INVALID_INPUT_ENUM(2007, HttpStatus.BAD_REQUEST.value(), "API Validation에 실패했습니다. Value 값으로 준 ENUM이 유효한 지 확인해주세요."),
    INVALID_FILE(2008, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 파일입니다."),
    IMAGE_UPLOAD_FAILED(2009, HttpStatus.BAD_REQUEST.value(), "이미지 업로드에 실패했습니다."),

    /**
     * 3000: 권한 오류
     */
    UNAUTHORIZED(3000, HttpStatus.UNAUTHORIZED.value(), "인증되지 않은 사용자입니다."),
    FORBIDDEN(3001, HttpStatus.FORBIDDEN.value(), "권한이 없습니다."),
    INVALID_TOKEN(3002, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(3003, HttpStatus.BAD_REQUEST.value(), "만료된 토큰입니다."),
    INVALID_REFRESH_TOKEN(3004, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 리프레시 토큰입니다."),
    UNAUTHORIZED_ROLE(3005, HttpStatus.FORBIDDEN.value(), "인가되지 않는 사용자입니다."),
    EXPIRED_REFRESH_TOKEN(3006, HttpStatus.BAD_REQUEST.value(), "만료된 리프레시 토큰입니다."),

    /**
     * 4000: 서버 오류
     */
    INTERNAL_SERVER_ERROR(4000, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류가 발생했습니다."),
    JSON_PROCESSING_ERROR(4001, HttpStatus.INTERNAL_SERVER_ERROR.value(), "JSON 처리 중 오류가 발생했습니다."),

    /**
     * 5000: 사용자 오류
     */
    USER_NOT_FOUND(5000, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다."),
    HOST_INSPECTION_NOT_FOUND(5100, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 호스트 승인 요청입니다."),


    /**
     * 6000: event, subject 오류
     */
    EVENT_NOT_FOUND(6000, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이벤트입니다."),
    INVALID_EVENT(6001, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 이벤트입니다."),
    INVALID_EVENT_RANGE(6002, HttpStatus.BAD_REQUEST.value(), "이벤트의 공개 상태를 확인해주세요."),
    EVENT_CODE_NOT_MATCH(6003, HttpStatus.BAD_REQUEST.value(), "이벤트 코드가 일치하지 않습니다."),
    EVENT_NOT_MATCH(6004, HttpStatus.BAD_REQUEST.value(), "사용자가 개최한 이벤트가 아닙니다."),
    SUBJECT_NOT_FOUND(6100, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이벤트 대상입니다."),
    SUPPORT_NOT_FOUND(6200, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 후원입니다."),
    SUPPORT_ALREADY_EXISTS(6201, HttpStatus.CONFLICT.value(), "이미 후원이 등록되었습니다."),
    SUPPORT_LOG_NOT_FOUND(6300, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 후원 로그입니다."),
    SUPPORT_LOG_FIND_ERROR(6301, HttpStatus.INTERNAL_SERVER_ERROR.value(), "후원 로그 조회 과정에서 에러가 발생했습니다."),
    APPROVAL_NOT_FOUND(6400, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 참여자 신청입니다."),
    APPROVAL_ALREADY_EXISTS(6401, HttpStatus.CONFLICT.value(), "이미 참여자 신청이 등록되었습니다."),
    EVENT_LOG_NOT_FOUND(6500, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이벤트 로그입니다."),
    EVENT_LOG_ALREADY_EXISTS(6501, HttpStatus.CONFLICT.value(), "이미 이벤트 로그가 존재합니다."),

    /**
     * 7000: report 오류
     */

    REPORT_NOT_FOUND(7000, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 신고입니다."),
    REPORT_ALREADY_EXISTS(7001, HttpStatus.CONFLICT.value(), "이미 해당 이벤트로 신고가 접수 되었습니다.")
    ;


    private final int code;

    private final int status;

    private final String message;


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
