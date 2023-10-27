package com.otaku.otakube.repository.approval;

import com.otaku.otakube.dto.approval.response.ApprovalResponseDto;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.log.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApprovalRepository extends JpaRepository<Approval, Long>, ApprovalRepositoryCustom {

    @Query(value = """
            select new com.otaku.otakube.dto.approval.response.ApprovalResponseDto
            (e.approvalId,
            e.xNickname,
            e.xId,
            e.status)
            from Approval e
            where e.event.eventId = :eventId
            order by e.status desc
            """)
    List<ApprovalResponseDto> findApprovalByEventId(@Param("eventId") final Long eventId);

    Optional<Approval> findApprovalByApprovalIdAndStatusIsLike(final Long eventId, final ApprovalStatus approvalStatus);
}
