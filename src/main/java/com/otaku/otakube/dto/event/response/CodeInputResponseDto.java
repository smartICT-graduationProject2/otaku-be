package com.otaku.otakube.dto.event.response;

import lombok.Getter;

@Getter
public class CodeInputResponseDto {

    private Boolean isRight;
    private String name;
    private String perksImage;

    public CodeInputResponseDto(Boolean isRight, String name, String perksImage) {
        this.isRight = isRight;
        this.name = name;
        this.perksImage = perksImage;
    }
}
