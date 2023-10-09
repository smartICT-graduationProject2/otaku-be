package com.otaku.otakube.repository.subject;

import com.otaku.otakube.entity.event.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long>, SubjectRepositoryCustom{
}
