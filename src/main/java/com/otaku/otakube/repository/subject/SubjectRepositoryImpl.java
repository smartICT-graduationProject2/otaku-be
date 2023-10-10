package com.otaku.otakube.repository.subject;

import com.otaku.otakube.dto.subject.response.SubjectResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.otaku.otakube.entity.event.QSubject.subject;

@AllArgsConstructor
@Repository
public class SubjectRepositoryImpl implements SubjectRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<SubjectResponseDto> findSubjectList(Pageable pageable, String category, Long lastId) {
        final List<SubjectResponseDto> subjectList = queryFactory
                .select(
                        Projections.constructor(
                                SubjectResponseDto.class,
                                subject.subjectId,
                                subject.name
                        )
                )
                .from(subject)
                .where(subject.category.eq(category),
                        subject.subjectId.gt(lastId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;


        if (subjectList.size() > pageable.getPageSize()){
            hasNext = true;
            subjectList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(subjectList, pageable, hasNext);
    }
}
