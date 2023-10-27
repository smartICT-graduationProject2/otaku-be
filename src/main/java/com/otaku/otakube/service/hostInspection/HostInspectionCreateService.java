package com.otaku.otakube.service.hostInspection;

import com.otaku.otakube.entity.user.HostInspection;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.HostInspectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HostInspectionCreateService {

    private final HostInspectionRepository hostInspectionRepository;

    public void createHostInspection(String authUrl,User user){
        hostInspectionRepository.save(
                HostInspection
                        .builder()
                        .user(user)
                        .authUrl(authUrl)
                        .build()
        );
    }

}
