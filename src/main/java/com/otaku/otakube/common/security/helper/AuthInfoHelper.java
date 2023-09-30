package com.otaku.otakube.common.security.helper;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import com.otaku.otakube.entity.user.Role;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.service.user.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class AuthInfoHelper {
    private final UserFindService userFindService;

    public User getUser() {
        final AuthInfo authInfo = getAuthInfo();

        return userFindService.findUserById(getAuthInfo().getId());
    }

    private AuthInfo getAuthInfo() {
        return (AuthInfo) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).orElseThrow(
                () -> CustomException.of(ErrorDetails.FORBIDDEN)
        );
    }

}
