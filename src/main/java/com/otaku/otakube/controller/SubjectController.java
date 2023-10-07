package com.otaku.otakube.controller;

import com.otaku.otakube.service.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    //대상 조회
    @GetMapping("")
    public List<String> findSubjects(String category) {
        return subjectService.findSubjects(category);
    }
}
