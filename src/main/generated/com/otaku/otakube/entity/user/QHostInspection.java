package com.otaku.otakube.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHostInspection is a Querydsl query type for HostInspection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHostInspection extends EntityPathBase<HostInspection> {

    private static final long serialVersionUID = 825398094L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHostInspection hostInspection = new QHostInspection("hostInspection");

    public final com.otaku.otakube.entity.common.QBaseTimeEntity _super = new com.otaku.otakube.entity.common.QBaseTimeEntity(this);

    public final StringPath authUrl = createString("authUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> hostInspectionId = createNumber("hostInspectionId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final EnumPath<com.otaku.otakube.entity.common.ApprovalStatus> status = createEnum("status", com.otaku.otakube.entity.common.ApprovalStatus.class);

    public final QUser user;

    public QHostInspection(String variable) {
        this(HostInspection.class, forVariable(variable), INITS);
    }

    public QHostInspection(Path<? extends HostInspection> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHostInspection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHostInspection(PathMetadata metadata, PathInits inits) {
        this(HostInspection.class, metadata, inits);
    }

    public QHostInspection(Class<? extends HostInspection> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

