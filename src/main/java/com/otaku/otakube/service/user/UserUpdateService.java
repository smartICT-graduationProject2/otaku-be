package com.otaku.otakube.service.user;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.UserRepository;
import com.otaku.otakube.service.common.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;
    private final HostInspectionCreateService hostInspectionCreateService;
    private final AwsS3Service awsS3Service;
    private final AuthInfoHelper authInfoHelper;


    public void withdrawUser() {

        User createdUser = authInfoHelper.getUser();

        createdUser.withdrawUser();

        userRepository.save(createdUser);
    }

    public void applyHost(MultipartFile applianceFile) {

        User createdUser = authInfoHelper.getUser();

        final String authUrl = awsS3Service.uploadFile(applianceFile);

        hostInspectionCreateService.createHostInspection(authUrl, createdUser);
    }

}
