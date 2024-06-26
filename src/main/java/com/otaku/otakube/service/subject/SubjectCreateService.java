package com.otaku.otakube.service.subject;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.dto.subject.request.SubjectRequestDto;
import com.otaku.otakube.repository.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectCreateService {
    private final SubjectRepository subjectRepository;

    public Long createSubject(final SubjectRequestDto dto){
        if (subjectRepository.existsByNameLike(dto.subjectName()+"%"))
            throw CustomException.of(ErrorDetails.SUBJECT_ALREADY_EXISTS);
        return subjectRepository.save(dto.toEntity()).getSubjectId();
    }
}
