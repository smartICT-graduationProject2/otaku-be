package com.otaku.otakube.repository.user;

import com.otaku.otakube.dto.user.response.MyPageAdmissionResponseDto;

import java.util.List;

public interface UserRepositoryCustom {

    //마이페이지 조회 입장권
    List<MyPageAdmissionResponseDto> findAdmissionList(Long userId);
}
