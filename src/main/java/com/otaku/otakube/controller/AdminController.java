package com.otaku.otakube.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    @GetMapping("/view")
    public String loginPage() {
        return "login"; // 홈 페이지,,
    }

    @GetMapping("/view/list")
    public String tablePage() {
        return "list"; // 홈 페이지
    }


}
