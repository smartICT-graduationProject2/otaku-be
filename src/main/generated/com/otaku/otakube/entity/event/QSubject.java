package com.otaku.otakube.entity.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubject is a Querydsl query type for Subject
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubject extends EntityPathBase<Subject> {

    private static final long serialVersionUID = 1409536615L;

    public static final QSubject subject = new QSubject("subject");

    public final StringPath category = createString("category");

    public final ListPath<Event, QEvent> eventList = this.<Event, QEvent>createList("eventList", Event.class, QEvent.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Long> subjectId = createNumber("subjectId", Long.class);

    public QSubject(String variable) {
        super(Subject.class, forVariable(variable));
    }

    public QSubject(Path<? extends Subject> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubject(PathMetadata metadata) {
        super(Subject.class, metadata);
    }

}

