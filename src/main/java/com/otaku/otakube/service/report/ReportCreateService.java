package com.otaku.otakube.service.report;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Report;
import com.otaku.otakube.repository.report.ReportRepository;
import com.otaku.otakube.service.event.EventReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportCreateService {

    private final ReportRepository reportRepository;
    private final EventReadService eventReadService;

    @Transactional
    public void createReport(final Long eventId){
        if (reportRepository.existsReportByEventId(eventId))
            throw EventException.of(ErrorDetails.REPORT_ALREADY_EXISTS);

        Event reportedEvent = eventReadService.findEventById(eventId);

        reportRepository.save(Report
                .builder()
                .event(reportedEvent)
                .build());
    }
}
