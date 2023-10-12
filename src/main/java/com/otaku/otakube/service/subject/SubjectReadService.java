package com.otaku.otakube.service.subject;

import com.otaku.otakube.dto.subject.response.SubjectResponseDto;
import com.otaku.otakube.repository.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectReadService {
    private final SubjectRepository subjectRepository;

    public Slice<SubjectResponseDto> getSubjectListByCategory(Pageable pageable, String category, Long lastSubjectId){
        return subjectRepository.findSubjectList(pageable, category, lastSubjectId);
    }

    public List<SubjectResponseDto> getAllSubjectList(){
        return subjectRepository.findAllSubjectList();
    }
}
