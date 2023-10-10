package com.otaku.otakube.entity.log;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApproval is a Querydsl query type for Approval
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApproval extends EntityPathBase<Approval> {

    private static final long serialVersionUID = -1946544322L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApproval approval = new QApproval("approval");

    public final com.otaku.otakube.entity.common.QBaseTimeEntity _super = new com.otaku.otakube.entity.common.QBaseTimeEntity(this);

    public final com.otaku.otakube.entity.user.QUser applicant;

    public final NumberPath<Long> approvalId = createNumber("approvalId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.otaku.otakube.entity.event.QEvent event;

    public final EnumPath<com.otaku.otakube.entity.common.ApprovalStatus> status = createEnum("status", com.otaku.otakube.entity.common.ApprovalStatus.class);

    public final StringPath xId = createString("xId");

    public final StringPath xNickname = createString("xNickname");

    public QApproval(String variable) {
        this(Approval.class, forVariable(variable), INITS);
    }

    public QApproval(Path<? extends Approval> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApproval(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApproval(PathMetadata metadata, PathInits inits) {
        this(Approval.class, metadata, inits);
    }

    public QApproval(Class<? extends Approval> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.applicant = inits.isInitialized("applicant") ? new com.otaku.otakube.entity.user.QUser(forProperty("applicant")) : null;
        this.event = inits.isInitialized("event") ? new com.otaku.otakube.entity.event.QEvent(forProperty("event"), inits.get("event")) : null;
    }

}

