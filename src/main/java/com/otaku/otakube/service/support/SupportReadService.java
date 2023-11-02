package com.otaku.otakube.service.support;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.dto.support.response.SupportInfoResponseDto;
import com.otaku.otakube.dto.support.response.SupportResponseDto;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.log.SupportLog;
import com.otaku.otakube.repository.support.SupportLogRepository;
import com.otaku.otakube.repository.support.SupportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupportReadService {

    private final SupportRepository supportRepository;
    private final SupportLogRepository supportLogRepository;


    @Transactional(readOnly = true)
    public Support findSupportById(final Long supportId) {
        return supportRepository.findById(supportId)
                .orElseThrow( () -> EventException.of(ErrorDetails.SUPPORT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public SupportInfoResponseDto findSupportByEventId(final Long supportId) {
        return supportRepository.findSupportInfoById(supportId)
                .orElseThrow( () -> EventException.of(ErrorDetails.SUPPORT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public SupportLog findSupportLogById(final Long supportLogId) {
        return supportLogRepository.findSupportLogsBySupportLogIdAndStatusLike(supportLogId, ApprovalStatus.RECEPTION)
                .orElseThrow( () -> EventException.of(ErrorDetails.SUPPORT_LOG_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<SupportResponseDto> findSupportLogListById(final Long supportId){
        try {
            return supportLogRepository.findSupportLogsBySupport(supportId);
        }catch (Exception e){
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            throw EventException.of(ErrorDetails.SUPPORT_LOG_FIND_ERROR);
        }
    }
}
