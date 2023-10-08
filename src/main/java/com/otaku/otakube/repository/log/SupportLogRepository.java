package com.otaku.otakube.repository.log;

import com.otaku.otakube.dto.event.response.SupporterFindResponseDto;
import com.otaku.otakube.entity.log.SupportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupportLogRepository extends JpaRepository<SupportLog, Long> {

    @Query("select new com.otaku.otakube.dto.event.response.SupporterFindResponseDto" +
            "(sl.supportLogId, sp.name, sl.authUrl, sl.supportAmount, sl.status)" +
            " from SupportLog sl join sl.supporter sp" +
            " where sl.support.supportId = :supportId")
    List<SupporterFindResponseDto> findSupporters(@Param("supportId") Long supportId);
}
