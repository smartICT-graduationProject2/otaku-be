package com.otaku.otakube.repository.event;

import com.otaku.otakube.dto.event.response.EventListResponseDto;
import com.otaku.otakube.entity.event.EventStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.otaku.otakube.entity.event.QEvent.event;
import static com.otaku.otakube.entity.event.QSubject.subject;
import static com.otaku.otakube.entity.log.QWishList.wishList;

@AllArgsConstructor
@Repository
public class EventRepositoryImpl implements EventRepositoryCustom {
    private final JPAQueryFactory queryFactory;

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
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;


        if (eventList.size() > pageable.getPageSize()){
            hasNext = true;
            eventList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(eventList, pageable, hasNext);
    }

}
