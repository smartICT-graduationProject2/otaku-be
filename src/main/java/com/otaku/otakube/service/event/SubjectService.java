package com.otaku.otakube.service.event;

import com.otaku.otakube.repository.event.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    /**
     * 대상 조회
     */
    public List<String> findSubjects(String category) {
        return subjectRepository.findByCategory(category);
    }
}
