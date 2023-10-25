package com.otaku.otakube.service.user;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.user.response.MyPageResponseDto;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageReadService {

    private final UserRepository userRepository;
    private final AuthInfoHelper authInfoHelper;

    //마이페이지 조회 입장권
    public List<MyPageResponseDto> findUserAdmission() {

        User user = authInfoHelper.getUser();

        return userRepository.findAdmissionList(user.getUserId());
    }

    //마이페이지 조회 특전
    public List<MyPageResponseDto> findUserPerks() {

        User user = authInfoHelper.getUser();

        return userRepository.findPerksList(user.getUserId());
    }
}
