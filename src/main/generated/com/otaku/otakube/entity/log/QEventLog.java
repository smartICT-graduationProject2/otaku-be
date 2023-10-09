package com.otaku.otakube.entity.log;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventLog is a Querydsl query type for EventLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventLog extends EntityPathBase<EventLog> {

    private static final long serialVersionUID = 1441278789L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventLog eventLog = new QEventLog("eventLog");

    public final com.otaku.otakube.entity.common.QBaseTimeEntity _super = new com.otaku.otakube.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.otaku.otakube.entity.event.QEvent event;

    public final NumberPath<Long> eventLogId = createNumber("eventLogId", Long.class);

    public final EnumPath<EventLogStatus> status = createEnum("status", EventLogStatus.class);

    public final com.otaku.otakube.entity.user.QUser user;

    public QEventLog(String variable) {
        this(EventLog.class, forVariable(variable), INITS);
    }

    public QEventLog(Path<? extends EventLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventLog(PathMetadata metadata, PathInits inits) {
        this(EventLog.class, metadata, inits);
    }

    public QEventLog(Class<? extends EventLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new com.otaku.otakube.entity.event.QEvent(forProperty("event"), inits.get("event")) : null;
        this.user = inits.isInitialized("user") ? new com.otaku.otakube.entity.user.QUser(forProperty("user")) : null;
    }

}

