package com.otaku.otakube.repository.event;

import com.otaku.otakube.dto.event.response.EventDetailResponseDto;
import com.otaku.otakube.dto.event.response.EventListResponseDto;
import com.otaku.otakube.dto.event.response.EventSearchResponseDto;
import com.otaku.otakube.entity.event.EventStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

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
                                wishList.status.isNotNull(),
                                event.createdAt
                        )
                )
                .from(event)
                .leftJoin(event.wishLists, wishList)
                .join(event.subject, subject)
                .join(event.support, support)
                .where( event.eventId.eq(eventId),
                        wishList.user.userId.eq(userId).or(wishList.user.isNull()),
                        event.status.notIn(EventStatus.CLOSED, EventStatus.DELETED))
                .fetchFirst());
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
                                wishList.status.isNotNull()
                        )
                )
                .from(event)
                .leftJoin(event.wishLists, wishList)
                .join(event.subject, subject)
                .where(subject.subjectId.eq(subjectId),
                        wishList.user.userId.eq(userId).or(wishList.user.isNull()),
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
                                wishList.status.isNotNull()
                        )
                )
                .from(event)
                .leftJoin(event.wishLists, wishList)
                .join(event.subject, subject)
                .where( eqTodayEvent(todayEvent, query),
                        wishList.user.userId.eq(userId).or(wishList.user.isNull()),
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

    private BooleanExpression eqTodayEvent(final boolean todayEvent, final String query){
        return todayEvent? event.openedDate.loe(LocalDate.now()).and(event.closedDate.goe(LocalDate.now())): subject.name.like("%"+query+"%");
    }

}
