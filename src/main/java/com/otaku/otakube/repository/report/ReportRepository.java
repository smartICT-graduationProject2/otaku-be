package com.otaku.otakube.repository.report;

import com.otaku.otakube.entity.event.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {
}
