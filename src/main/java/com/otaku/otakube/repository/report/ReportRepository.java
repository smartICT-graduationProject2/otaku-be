package com.otaku.otakube.repository.report;

import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.event.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {
    Optional<Report> findReportByReportIdAndStatus(final Long reportId, final ApprovalStatus status);
}
