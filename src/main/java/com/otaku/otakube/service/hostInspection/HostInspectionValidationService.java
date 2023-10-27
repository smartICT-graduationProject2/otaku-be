package com.otaku.otakube.service.hostInspection;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.repository.hostInspection.HostInspectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HostInspectionValidationService {

    private final HostInspectionRepository hostInspectionRepository;

    public void validateDuplicateHostInspection(final Long userId){
        if (hostInspectionRepository.existsHostInspectionByUser(userId))
            throw CustomException.of(ErrorDetails.HOST_INSPECTION_ALREADY_EXISTS);
    }

}
