package com.otaku.otakube.service.subject;

import com.otaku.otakube.dto.subject.request.SubjectRequestDto;
import com.otaku.otakube.dto.subject.response.SubjectResponseDto;
import com.otaku.otakube.repository.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectCreateService {
    private final SubjectRepository subjectRepository;

    public void createSubject(final SubjectRequestDto dto){
        subjectRepository.save(dto.toEntity());
    }
}
