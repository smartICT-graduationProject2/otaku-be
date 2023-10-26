package com.otaku.otakube.repository.report;

import com.otaku.otakube.dto.report.response.ReportResponseDto;
import com.otaku.otakube.dto.subject.response.SubjectResponseDto;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.otaku.otakube.entity.event.QReport.report;
import static com.otaku.otakube.entity.event.QSubject.subject;

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

    @Override
    public List<ReportResponseDto> findReportList() {
        Expression<String> concatenatedExpression = Expressions.stringTemplate(
                "concat('http://3.37.186.131:8080/events/', {0})", report.event.eventId.stringValue()
        );

        return queryFactory
                .select(
                        Projections.constructor(
                                ReportResponseDto.class,
                                report.reportId,
                                concatenatedExpression
                        )
                )
                .from(report)
                .where(report.status.eq(ApprovalStatus.RECEPTION))
                .fetch();
    }
}
