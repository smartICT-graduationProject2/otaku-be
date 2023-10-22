package com.otaku.otakube.service.support;

import com.otaku.otakube.entity.log.SupportLog;
import com.otaku.otakube.repository.support.SupportLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupportUpdateService {
    private final SupportLogRepository supportLogRepository;
    private final SupportReadService supportReadService;

    @Transactional
    public void approveSupportLog(final Long supportLogId){

        SupportLog supportLogForApproval = supportReadService.findSupportLogById(supportLogId);

        supportLogForApproval.approvedSupport();

        supportLogRepository.save(supportLogForApproval);
    }

}
