package com.otaku.otakube.common.config.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheType {
    EVENT_DETAIL_INFO("eventDetailInfo", 12 * 60, 100), //12시간
    USER_INFO("userInfo", 12 * 60, 100) //12시간
    ;

    private final String cacheName;
    private final int expiredAfterWrite;
    private final int maximumSize;
}
