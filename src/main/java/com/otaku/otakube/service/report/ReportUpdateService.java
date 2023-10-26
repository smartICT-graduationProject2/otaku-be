package com.otaku.otakube.service.report;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.event.Report;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportUpdateService {

    private final ReportRepository reportRepository;
    private final EventRepository eventRepository;

    @Transactional
    public void updateReport(final Long reportId, final Boolean inspectionResult){
        Report updatedReport = reportRepository.findReportByReportIdAndStatus(reportId, ApprovalStatus.RECEPTION)
                .orElseThrow( () -> EventException.of(ErrorDetails.REPORT_NOT_FOUND));

        if(Boolean.FALSE.equals(inspectionResult)){
            reportRepository.delete(updatedReport);
            return;
        }

        updatedReport.approveReport();
        eventRepository.delete(updatedReport.getEvent());
    }
}
