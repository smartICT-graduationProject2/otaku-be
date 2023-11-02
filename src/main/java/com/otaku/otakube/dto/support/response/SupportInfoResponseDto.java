package com.otaku.otakube.dto.support.response;

import java.io.Serializable;

/**
 * DTO for {@link com.otaku.otakube.entity.event.Support}
 */
public record SupportInfoResponseDto(Long supportId, String bank, String accountAddress,
                                     String accountHolder) implements Serializable {
}