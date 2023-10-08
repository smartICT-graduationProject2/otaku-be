package com.otaku.otakube.repository.log;

import com.otaku.otakube.dto.event.response.AuthenticationFindResponseDto;
import com.otaku.otakube.entity.log.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    @Query("select new com.otaku.otakube.dto.event.response.AuthenticationFindResponseDto" +
            "(a.authenticationId, u.name, a.xNickname, a.status)" +
            " from Authentication a join a.user u" +
            " where a.event.eventId = :eventId")
    List<AuthenticationFindResponseDto> findAuthentications(@Param("eventId") Long eventId);
}
