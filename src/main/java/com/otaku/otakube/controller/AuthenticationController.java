package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.response.AuthenticationFindResponseDto;
import com.otaku.otakube.service.log.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentications")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    //개최자의 참여자 조회
    @GetMapping("")
    public List<AuthenticationFindResponseDto> findAuthentications(Long eventId) {
        return authenticationService.findAuthentication(eventId);
    }
}
