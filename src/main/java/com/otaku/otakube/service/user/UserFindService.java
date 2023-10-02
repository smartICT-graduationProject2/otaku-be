package com.otaku.otakube.service.user;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.user.UserException;
import com.otaku.otakube.entity.user.ActiveStatus;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findUserByUserIdAndStatus(userId, ActiveStatus.ACTIVE).orElseThrow(
                () -> UserException.of(ErrorDetails.USER_NOT_FOUND)
        );
    }

    public User findUserByEmail(String userEmail) {
        return userRepository.findUserByEmailAndStatus(userEmail, ActiveStatus.ACTIVE).orElseThrow(
                () -> UserException.of(ErrorDetails.USER_NOT_FOUND)
        );
    }
}
