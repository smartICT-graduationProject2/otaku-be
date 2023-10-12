package com.otaku.otakube.dto.user.request;

import com.otaku.otakube.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(name = "유저 로그인 및 회원가입 dto")
public record UserLoginRequestDto(
        @Schema(description = "이름", defaultValue = "홍길동", example = "홍길동")
        @NotBlank(message = "이름은 필수 입력값입니다.")
        String name,
        @Schema(description = "이메일", defaultValue = "test@gmail.com", example = "test@gmail.com")
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
        String email
) {
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }
}
