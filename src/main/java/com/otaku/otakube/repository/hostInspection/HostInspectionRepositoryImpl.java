package com.otaku.otakube.repository.hostInspection;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.otaku.otakube.entity.user.QHostInspection.hostInspection;

@RequiredArgsConstructor
@Repository
public class HostInspectionRepositoryImpl implements HostInspectionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsHostInspectionByUser(Long userId) {
        return queryFactory
                .selectFrom(hostInspection)
                .where(hostInspection.user.userId.eq(userId))
                .fetchFirst() != null;
    }
}
