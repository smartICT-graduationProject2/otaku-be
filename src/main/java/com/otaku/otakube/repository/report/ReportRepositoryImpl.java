package com.otaku.otakube.repository.report;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.otaku.otakube.entity.event.QReport.report;

@RequiredArgsConstructor
@Repository
public class ReportRepositoryImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsReportByEventId(Long eventId) {
        return queryFactory
                .selectFrom(report)
                .where(report.event.eventId.eq(eventId))
                .fetchFirst() != null;
    }
}
