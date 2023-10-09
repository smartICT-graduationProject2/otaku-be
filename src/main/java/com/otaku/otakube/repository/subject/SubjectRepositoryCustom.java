package com.otaku.otakube.repository.subject;

import com.otaku.otakube.dto.subject.response.SubjectResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SubjectRepositoryCustom {
    Slice<SubjectResponseDto> findSubjectList(Pageable pageable, String category, Long lastId);
}
