package com.otaku.otakube.repository.support;

import com.otaku.otakube.dto.support.response.SupportResponseDto;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.log.SupportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SupportLogRepository extends JpaRepository<SupportLog, Long> {
    @Query(value = """
            select new com.otaku.otakube.dto.support.response.SupportResponseDto
            (sl.supportLogId,
            sl.accountHolder,
            sl.supportAmount,
            sl.status)
            from SupportLog as sl
            where sl.support.supportId = :supportId
            """)
    List<SupportResponseDto> findSupportLogsBySupport(@Param("supportId") final Long supportId);

    Optional<SupportLog> findSupportLogsBySupportLogIdAndStatusLike(final Long supportLogId, final ApprovalStatus status);
}
