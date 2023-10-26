package com.otaku.otakube.repository.report;

import com.otaku.otakube.dto.report.response.ReportResponseDto;

import java.util.List;

public interface ReportRepositoryCustom {
    boolean existsReportByEventId(final Long eventId);

    List<ReportResponseDto> findReportList();
}
