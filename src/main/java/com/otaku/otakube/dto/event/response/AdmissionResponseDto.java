package com.otaku.otakube.dto.event.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdmissionResponseDto {

    private String name;
    private LocalDateTime createAt;
    private String xNickname;

    public AdmissionResponseDto(String name, LocalDateTime createAt, String xNickname) {
        this.name = name;
        this.createAt = createAt;
        this.xNickname = xNickname;
    }
}
