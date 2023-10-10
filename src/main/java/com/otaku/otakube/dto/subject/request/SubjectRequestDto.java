package com.otaku.otakube.dto.subject.request;

import com.otaku.otakube.entity.event.Subject;

public record SubjectRequestDto (
        String category,
        String subjectName
){
    public Subject toEntity(){
        return Subject.builder()
                .category(category)
                .name(subjectName)
                .build();
    }
}
