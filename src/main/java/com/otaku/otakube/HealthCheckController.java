package com.otaku.otakube;

import com.otaku.otakube.common.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class HealthCheckController {

    @GetMapping("/health-check")
    public ResponseEntity<BaseResponseDto<String>> healthCheck() {

        return BaseResponseDto.success("Health Check Success!");
    }
}
