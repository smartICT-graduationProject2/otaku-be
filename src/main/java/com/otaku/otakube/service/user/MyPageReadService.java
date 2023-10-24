package com.otaku.otakube.service.user;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.user.response.MyPageAdmissionResponseDto;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageReadService {

    private final UserRepository userRepository;
    private final AuthInfoHelper authInfoHelper;

    //마이페이지 조회 입장권
    public List<MyPageAdmissionResponseDto> findUserAdmission() {

        User user = authInfoHelper.getUser();

        return userRepository.findAdmissionList(user.getUserId());
    }
}
