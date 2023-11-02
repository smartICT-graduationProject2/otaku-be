package com.otaku.otakube.repository.subject;

import com.otaku.otakube.dto.subject.response.SubjectResponseDto;
import com.otaku.otakube.entity.event.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>, SubjectRepositoryCustom{
    @Query(value = """
            select new com.otaku.otakube.dto.subject.response.SubjectResponseDto
            (e.subjectId,
            e.name)
            from Subject e
            """)
    List<SubjectResponseDto> findAllSubjectList();

    boolean existsByNameLike(String name);


}
