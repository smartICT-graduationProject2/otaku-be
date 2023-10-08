package com.otaku.otakube.repository.event;

import com.otaku.otakube.entity.event.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("select r from Report r where r.event.eventId = :eventId")
    Report findByEventId(@Param("eventId") Long eventId);
}
