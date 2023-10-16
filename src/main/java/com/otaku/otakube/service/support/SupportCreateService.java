package com.otaku.otakube.service.support;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.support.request.SupportRegisterRequestDto;
import com.otaku.otakube.dto.support.request.SupportRequestDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.log.SupportLog;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.support.SupportLogRepository;
import com.otaku.otakube.repository.support.SupportRepository;
import com.otaku.otakube.service.common.AwsS3Service;
import com.otaku.otakube.service.event.EventReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SupportCreateService {
    private final SupportRepository supportRepository;
    private final SupportLogRepository supportLogRepository;
    private final EventReadService eventReadService;
    private final SupportReadService supportReadService;
    private final AuthInfoHelper authInfoHelper;
    private final AwsS3Service awsS3Service;

    @Transactional
    public void createSupport(final SupportRequestDto dto, final Long eventId){
        Event eventForCreatedSupport = eventReadService.findEventById(eventId);

        if (eventForCreatedSupport.getSupport() != null)
            throw CustomException.of(ErrorDetails.SUPPORT_ALREADY_EXISTS);

        supportRepository.save(
                Support.builder()
                        .event(eventForCreatedSupport)
                        .accountAddress(dto.accountAddress())
                        .accountHolder(dto.accountHolder())
                        .targetAmount(dto.targetAmount())
                        .build()
        );
    }

    @Transactional
    public void createSupportLog(final SupportRegisterRequestDto dto, final Long supportId, final MultipartFile supportImageFile){
        User supporter = authInfoHelper.getUser();
        Support targetSupport = supportReadService.findSupportById(supportId);
        String authUrl = awsS3Service.uploadFile(supportImageFile);

        supportLogRepository.save(
                SupportLog.builder()
                        .support(targetSupport)
                        .supporter(supporter)
                        .supportAmount(dto.supportAmount())
                        .authUrl(authUrl)
                        .build()
        );
    }

}
