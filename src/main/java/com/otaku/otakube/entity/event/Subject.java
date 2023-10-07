package com.otaku.otakube.entity.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", updatable = false)
    private Long subjectId;

    private String category;

    private String name;

    @Builder
    public Subject(String category, String name) {
        this.category = category;
        this.name = name;
    }
}
