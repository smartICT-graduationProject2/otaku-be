package com.otaku.otakube.repository.approval;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.otaku.otakube.entity.event.QEvent.event;
import static com.otaku.otakube.entity.log.QApproval.approval;

@AllArgsConstructor
@Repository
public class ApprovalRepositoryImpl implements ApprovalRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsApproval(Long eventId, Long userId) {
        return queryFactory
                .selectFrom(approval)
                .where(
                        approval.event.eventId.eq(eventId),
                        approval.applicant.userId.eq(userId)
                )
                .fetchFirst() != null;
    }

    @Override
    public boolean validateApprovalPermission(Long approvalId, Long userId) {
        return queryFactory
                .selectFrom(approval)
                .join(approval.event, event)
                .where(
                        event.hostUser.userId.eq(userId),
                        approval.approvalId.eq(approvalId)
                )
                .fetchFirst() != null;
    }
}
