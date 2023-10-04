package com.otaku.otakube.entity.event;

/**
 * UNDEFINED : 확정되지 않은 이벤트
 * PREPARATION : 준비
 * ACTIVE : 모금 완료
 * CLOSED : 이벤트 끝
 * DELETED : 삭제된 이벤트
 */
public enum EventStatus {
    UNDEFINED, PREPARATION, ACTIVE, CLOSED, DELETED
}
