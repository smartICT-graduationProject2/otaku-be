package com.otaku.otakube.service.log;

import com.otaku.otakube.dto.event.response.AuthenticationFindResponseDto;
import com.otaku.otakube.entity.log.Authentication;
import com.otaku.otakube.repository.log.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    /**
     * 개최자의 참여자 조회
     */
    public List<AuthenticationFindResponseDto> findAuthentication(Long eventId) {
        return authenticationRepository.findAuthentications(eventId);
    }

    /**
     * 개최자의 참여자 처리
     */
    @Transactional
    public void approveAuthentication(Long authenticationId, Boolean isRight) {

        Authentication authentication = authenticationRepository.findById(authenticationId).get();

        authentication.changeStatus(isRight);
    }
}
