package com.otaku.otakube.service.user;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;
    private final AuthInfoHelper authInfoHelper;


    public void withdrawUser() {

        User createdUser = authInfoHelper.getUser();

        createdUser.withdrawUser();

        userRepository.save(createdUser);
    }

    @CacheEvict(value = "userInfo", key = "'user-' + #userId")
    public void updateUserToHost(User userForHost, final Long userId){
        userForHost.updateHostRole();
        userRepository.save(userForHost);
    }

}
