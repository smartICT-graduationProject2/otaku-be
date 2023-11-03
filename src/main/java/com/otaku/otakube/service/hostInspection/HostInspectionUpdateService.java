package com.otaku.otakube.service.hostInspection;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.user.UserException;
import com.otaku.otakube.entity.user.HostInspection;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.hostInspection.HostInspectionRepository;
import com.otaku.otakube.service.user.UserUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HostInspectionUpdateService {

    private final HostInspectionRepository hostInspectionRepository;
    private final UserUpdateService userUpdateService;

    @Transactional
    public void updateHostInspection(final Long hostInspectionId, final Boolean inspectionResult){
        HostInspection updatedHostInspection = hostInspectionRepository.findById(hostInspectionId)
                .orElseThrow( () -> UserException.of(ErrorDetails.HOST_INSPECTION_NOT_FOUND));

        if(Boolean.FALSE.equals(inspectionResult)){
            hostInspectionRepository.delete(updatedHostInspection);
            return;
        }

        updatedHostInspection.approveHostInspection();

        User userForHost = updatedHostInspection.getUser();
        userUpdateService.updateUserToHost(userForHost, userForHost.getUserId());
        hostInspectionRepository.save(updatedHostInspection);
    }

}
