package com.otaku.otakube.service.support;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.dto.support.request.SupportRequestDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.repository.support.SupportRepository;
import com.otaku.otakube.service.event.EventReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupportCreateService {
    private final SupportRepository supportRepository;
    private final EventReadService eventReadService;


    @Transactional
    public void createSupport(final SupportRequestDto dto, final Long eventId){
        Event eventForCreatedSupport = eventReadService.findEventById(eventId);

        if (eventForCreatedSupport.getSupport() != null)
            throw CustomException.of(ErrorDetails.EVENT_NOT_FOUND);

        supportRepository.save(
                Support.builder()
                        .event(eventForCreatedSupport)
                        .accountAddress(dto.accountAddress())
                        .accountHolder(dto.accountHolder())
                        .targetAmount(dto.targetAmount())
                        .build()
        );
    }
}
