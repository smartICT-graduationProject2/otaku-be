package com.otaku.otakube.entity.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSupport is a Querydsl query type for Support
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSupport extends EntityPathBase<Support> {

    private static final long serialVersionUID = 1422654730L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSupport support = new QSupport("support");

    public final StringPath accountAddress = createString("accountAddress");

    public final StringPath accountHolder = createString("accountHolder");

    public final NumberPath<Long> currentAmount = createNumber("currentAmount", Long.class);

    public final QEvent event;

    public final EnumPath<SupportStatus> status = createEnum("status", SupportStatus.class);

    public final NumberPath<Long> supportId = createNumber("supportId", Long.class);

    public final ListPath<com.otaku.otakube.entity.log.SupportLog, com.otaku.otakube.entity.log.QSupportLog> supportLogs = this.<com.otaku.otakube.entity.log.SupportLog, com.otaku.otakube.entity.log.QSupportLog>createList("supportLogs", com.otaku.otakube.entity.log.SupportLog.class, com.otaku.otakube.entity.log.QSupportLog.class, PathInits.DIRECT2);

    public final NumberPath<Long> targetAmount = createNumber("targetAmount", Long.class);

    public QSupport(String variable) {
        this(Support.class, forVariable(variable), INITS);
    }

    public QSupport(Path<? extends Support> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSupport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSupport(PathMetadata metadata, PathInits inits) {
        this(Support.class, metadata, inits);
    }

    public QSupport(Class<? extends Support> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event"), inits.get("event")) : null;
    }

}

