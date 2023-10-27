package com.otaku.otakube.service.hostInspection;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.entity.user.HostInspection;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.hostInspection.HostInspectionRepository;
import com.otaku.otakube.service.common.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class HostInspectionCreateService {

    private final HostInspectionRepository hostInspectionRepository;
    private final HostInspectionValidationService hostInspectionValidationService;
    private final AwsS3Service awsS3Service;
    private final AuthInfoHelper authInfoHelper;


    public void applyHost(MultipartFile applianceFile) {

        User createdUser = authInfoHelper.getUser();

        hostInspectionValidationService.validateDuplicateHostInspection(createdUser.getUserId());

        final String authUrl = awsS3Service.uploadFile(applianceFile);

        hostInspectionRepository.save(
                HostInspection
                        .builder()
                        .user(createdUser)
                        .authUrl(authUrl)
                        .build()
        );
    }

}
