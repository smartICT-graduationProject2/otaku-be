package com.otaku.otakube.repository.event;

import com.otaku.otakube.dto.event.response.EventDetailResponseDto;
import com.otaku.otakube.dto.event.response.EventHostResponseDto;
import com.otaku.otakube.dto.event.response.EventListResponseDto;
import com.otaku.otakube.dto.event.response.EventSearchResponseDto;
import com.otaku.otakube.entity.event.EventStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.otaku.otakube.entity.event.QEvent.event;
import static com.otaku.otakube.entity.event.QSubject.subject;
import static com.otaku.otakube.entity.event.QSupport.support;
import static com.otaku.otakube.entity.log.QWishList.wishList;

@AllArgsConstructor
@Repository
public class EventRepositoryImpl implements EventRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<EventDetailResponseDto> findEventDetailInfo(final Long eventId, final Long userId) {
        return Optional.ofNullable(queryFactory
                .select(
                        Projections.constructor(
                                EventDetailResponseDto.class,
                                event.eventId,
                                event.isPublic,
                                event.featuredImage,
                                event.name,
                                event.description,
                                event.xNickname,
                                event.xId,
                                subject.category,
                                subject.name,
                                event.address,
                                event.status,
                                support.supportId,
                                support.currentAmount,
                                support.targetAmount,
                                JPAExpressions
                                        .selectOne()
                                        .from(wishList)
                                        .where(
                                                wishList.user.userId.eq(userId),
                                                wishList.event.eventId.eq(event.eventId)
                                        )
                                        .exists(),
                                event.createdAt
                        )
                )
                .from(event)
                .leftJoin(event.support, support)
                .join(event.subject, subject)
                .where( event.eventId.eq(eventId),
                        event.status.notIn(EventStatus.CLOSED, EventStatus.DELETED))
                .fetchFirst());
    }

    @Override
    public Slice<EventHostResponseDto> findEventListByHostId(Pageable pageable, Long hostId) {
        final List<EventHostResponseDto> eventList = queryFactory
                .select(
                        Projections.constructor(
                                EventHostResponseDto.class,
                                event.eventId,
                                event.featuredImage,
                                event.name,
                                event.xNickname,
                                event.xId,
                                support.supportId,
                                subject.name,
                                event.code,
                                event.status
                        )
                )
                .from(event)
                .leftJoin(event.support, support)
                .join(event.subject, subject)
                .where(event.hostUser.userId.eq(hostId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1L)
                .orderBy(event.openedDate.desc())
                .fetch();

        boolean hasNext = false;


        if (eventList.size() > pageable.getPageSize()){
            hasNext = true;
            eventList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(eventList, pageable, hasNext);

    }

    @Override
    public Slice<EventListResponseDto> findEventListBySubjectId(Pageable pageable, final Long subjectId, final Long userId) {
        final List<EventListResponseDto> eventList = queryFactory
                .select(
                        Projections.constructor(
                                EventListResponseDto.class,
                                event.eventId,
                                event.featuredImage,
                                event.name,
                                event.xNickname,
                                event.xId,
                                subject.name,
                                event.address,
                                event.status,
                                JPAExpressions
                                        .selectOne()
                                        .from(wishList)
                                        .where(
                                                wishList.user.userId.eq(userId),
                                                wishList.event.eventId.eq(event.eventId)
                                        )
                                        .exists()
                        )
                )
                .from(event)
                .join(event.subject, subject)
                .where(subject.subjectId.eq(subjectId),
                        event.status.notIn(EventStatus.CLOSED, EventStatus.DELETED))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1L)
                .fetch();

        boolean hasNext = false;


        if (eventList.size() > pageable.getPageSize()){
            hasNext = true;
            eventList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(eventList, pageable, hasNext);
    }

    @Override
    public Slice<EventSearchResponseDto> findEventListByCondition(Pageable pageable, final boolean todayEvent, final String query, final Long userId) {
        final List<EventSearchResponseDto> eventList = queryFactory
                .select(
                        Projections.constructor(
                                EventSearchResponseDto.class,
                                event.eventId,
                                event.featuredImage,
                                event.name,
                                event.xNickname,
                                event.xId,
                                subject.name,
                                event.status,
                                JPAExpressions
                                        .selectOne()
                                        .from(wishList)
                                        .where(
                                                wishList.user.userId.eq(userId),
                                                wishList.event.eventId.eq(event.eventId)
                                        )
                                        .exists()
                        )
                )
                .from(event)
                .join(event.subject, subject)
                .where( eqTodayEvent(todayEvent, query),
                        event.status.notIn(EventStatus.CLOSED, EventStatus.DELETED))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1L)
                .fetch();

        boolean hasNext = false;


        if (eventList.size() > pageable.getPageSize()){
            hasNext = true;
            eventList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(eventList, pageable, hasNext);
    }

    @Override
    public Slice<EventSearchResponseDto> findEventListByWishList(Pageable pageable, final Long userId) {
        final List<EventSearchResponseDto> eventList = queryFactory
                .select(
                        Projections.constructor(
                                EventSearchResponseDto.class,
                                event.eventId,
                                event.featuredImage,
                                event.name,
                                event.xNickname,
                                event.xId,
                                subject.name,
                                event.status,
                                wishList.status.isNotNull()
                        )
                )
                .from(event)
                .join(event.wishLists, wishList)
                .join(event.subject, subject)
                .where( wishList.user.userId.eq(userId),
                        event.status.notIn(EventStatus.CLOSED, EventStatus.DELETED))
                .orderBy(event.closedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1L)
                .fetch();

        boolean hasNext = false;


        if (eventList.size() > pageable.getPageSize()){
            hasNext = true;
            eventList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(eventList, pageable, hasNext);
    }

    @Override
    public boolean existsEventByUserId(final Long eventId, final Long userId) {
        return queryFactory
                .selectFrom(event)
                .where(
                        event.eventId.eq(eventId),
                        event.hostUser.userId.eq(userId)
                )
                .fetchFirst() != null;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void markEventAsActive() {
        queryFactory
                .update(event)
                .set(event.status, EventStatus.ACTIVE)
                .where(
                        event.openedDate.goe(LocalDate.now()),
                        event.status.eq(EventStatus.PREPARATION)
                )
                .execute();
    }

    private BooleanExpression eqTodayEvent(final boolean todayEvent, final String query){
        return todayEvent? event.openedDate.loe(LocalDate.now()).and(event.closedDate.goe(LocalDate.now())): subject.name.like("%"+query+"%");
    }

}
