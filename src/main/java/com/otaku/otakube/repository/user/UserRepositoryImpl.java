package com.otaku.otakube.repository.user;

import com.otaku.otakube.dto.user.response.MyPageAdmissionResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

import static com.otaku.otakube.entity.event.QEvent.event;
import static com.otaku.otakube.entity.log.QEventLog.eventLog;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MyPageAdmissionResponseDto> findAdmissionList(Long userId) {

        LocalDate now = LocalDate.now(); //현재 날짜

        return queryFactory
                .select(Projections.constructor(
                        MyPageAdmissionResponseDto.class,
                        event.eventId,
                        event.name,
                        event.openedDate))
                .from(eventLog)
                .join(eventLog.event, event)
                .where(eventLog.user.userId.eq(userId),
                        event.openedDate.loe(now), //openedDate <= now
                        event.closedDate.goe(now)) //closedDate >= now
                .fetch();
    }
}