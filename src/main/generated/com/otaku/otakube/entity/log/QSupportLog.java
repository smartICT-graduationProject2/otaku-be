package com.otaku.otakube.entity.log;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSupportLog is a Querydsl query type for SupportLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSupportLog extends EntityPathBase<SupportLog> {

    private static final long serialVersionUID = 508871472L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSupportLog supportLog = new QSupportLog("supportLog");

    public final com.otaku.otakube.entity.common.QBaseTimeEntity _super = new com.otaku.otakube.entity.common.QBaseTimeEntity(this);

    public final StringPath authUrl = createString("authUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<com.otaku.otakube.entity.common.ApprovalStatus> status = createEnum("status", com.otaku.otakube.entity.common.ApprovalStatus.class);

    public final com.otaku.otakube.entity.event.QSupport support;

    public final NumberPath<Long> supportAmount = createNumber("supportAmount", Long.class);

    public final com.otaku.otakube.entity.user.QUser supporter;

    public final NumberPath<Long> supportLogId = createNumber("supportLogId", Long.class);

    public QSupportLog(String variable) {
        this(SupportLog.class, forVariable(variable), INITS);
    }

    public QSupportLog(Path<? extends SupportLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSupportLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSupportLog(PathMetadata metadata, PathInits inits) {
        this(SupportLog.class, metadata, inits);
    }

    public QSupportLog(Class<? extends SupportLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.support = inits.isInitialized("support") ? new com.otaku.otakube.entity.event.QSupport(forProperty("support"), inits.get("support")) : null;
        this.supporter = inits.isInitialized("supporter") ? new com.otaku.otakube.entity.user.QUser(forProperty("supporter"), inits.get("supporter")) : null;
    }

}

