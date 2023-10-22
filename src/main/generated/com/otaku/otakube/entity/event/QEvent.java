package com.otaku.otakube.entity.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -230423915L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvent event = new QEvent("event");

    public final com.otaku.otakube.entity.common.QBaseTimeEntity _super = new com.otaku.otakube.entity.common.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final ListPath<com.otaku.otakube.entity.log.Approval, com.otaku.otakube.entity.log.QApproval> approvalList = this.<com.otaku.otakube.entity.log.Approval, com.otaku.otakube.entity.log.QApproval>createList("approvalList", com.otaku.otakube.entity.log.Approval.class, com.otaku.otakube.entity.log.QApproval.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> closedDate = createDate("closedDate", java.time.LocalDate.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final StringPath featuredImage = createString("featuredImage");

    public final com.otaku.otakube.entity.user.QUser hostUser;

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> openedDate = createDate("openedDate", java.time.LocalDate.class);

    public final StringPath perksImage = createString("perksImage");

    public final EnumPath<EventStatus> status = createEnum("status", EventStatus.class);

    public final QSubject subject;

    public final QSupport support;

    public final ListPath<com.otaku.otakube.entity.log.WishList, com.otaku.otakube.entity.log.QWishList> wishLists = this.<com.otaku.otakube.entity.log.WishList, com.otaku.otakube.entity.log.QWishList>createList("wishLists", com.otaku.otakube.entity.log.WishList.class, com.otaku.otakube.entity.log.QWishList.class, PathInits.DIRECT2);

    public final StringPath xId = createString("xId");

    public final StringPath xNickname = createString("xNickname");

    public QEvent(String variable) {
        this(Event.class, forVariable(variable), INITS);
    }

    public QEvent(Path<? extends Event> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvent(PathMetadata metadata, PathInits inits) {
        this(Event.class, metadata, inits);
    }

    public QEvent(Class<? extends Event> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostUser = inits.isInitialized("hostUser") ? new com.otaku.otakube.entity.user.QUser(forProperty("hostUser")) : null;
        this.subject = inits.isInitialized("subject") ? new QSubject(forProperty("subject")) : null;
        this.support = inits.isInitialized("support") ? new QSupport(forProperty("support"), inits.get("support")) : null;
    }

}

