package com.otaku.otakube.service.report;

import com.otaku.otakube.dto.report.response.ReportResponseDto;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.event.Report;
import com.otaku.otakube.repository.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportReadService {

    private final ReportRepository reportRepository;

    @Transactional(readOnly = true)
    public List<ReportResponseDto> readAllReport(){
        return reportRepository.findReportList();
    }
}
