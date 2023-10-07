package com.otaku.otakube.repository.event;

import com.otaku.otakube.entity.event.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select s.name from Subject s where s.category = :category")
    List<String> findByCategory(@Param("category") String category);
}
