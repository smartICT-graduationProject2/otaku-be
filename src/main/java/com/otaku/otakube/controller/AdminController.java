package com.otaku.otakube.controller;


import com.otaku.otakube.dto.admin.request.adminLoginRequestDto;
import com.otaku.otakube.entity.user.HostInspection;
import com.otaku.otakube.repository.user.HostInspectionRepository;
import com.otaku.otakube.service.user.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final HostInspectionRepository hostInspectionRepository;

    @GetMapping
    public String loginPage() {
        return "login"; // 홈 페이지
    }

    @GetMapping("/table")
    public String showTable(Model model) {
        List<HostInspection> allList = hostInspectionRepository.findAll().stream().toList();
        model.addAttribute("tableData", allList); // 모델에 데이터를 추가하여 뷰로 전달
        return "table"; // 렌더링할 타임리프 템플릿 이름
    }
}
