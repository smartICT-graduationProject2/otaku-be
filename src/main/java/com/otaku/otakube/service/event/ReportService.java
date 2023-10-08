package com.otaku.otakube.service.event;

import com.otaku.otakube.entity.event.Report;
import com.otaku.otakube.repository.event.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    /**
     * 이벤트 신고 처리
     */
    public void approveReport(Long eventId, Boolean isApproved) {

        if (isApproved == null) isApproved = true;

        Report report = reportRepository.findByEventId(eventId);
        report.changeStatus(isApproved);
    }
}
