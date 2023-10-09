package com.otaku.otakube.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 548905053L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.otaku.otakube.entity.common.QBaseTimeEntity _super = new com.otaku.otakube.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final ListPath<com.otaku.otakube.entity.event.Event, com.otaku.otakube.entity.event.QEvent> eventList = this.<com.otaku.otakube.entity.event.Event, com.otaku.otakube.entity.event.QEvent>createList("eventList", com.otaku.otakube.entity.event.Event.class, com.otaku.otakube.entity.event.QEvent.class, PathInits.DIRECT2);

    public final ListPath<com.otaku.otakube.entity.log.EventLog, com.otaku.otakube.entity.log.QEventLog> eventLogs = this.<com.otaku.otakube.entity.log.EventLog, com.otaku.otakube.entity.log.QEventLog>createList("eventLogs", com.otaku.otakube.entity.log.EventLog.class, com.otaku.otakube.entity.log.QEventLog.class, PathInits.DIRECT2);

    public final QHostInspection hostInspection;

    public final StringPath name = createString("name");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final EnumPath<ActiveStatus> status = createEnum("status", ActiveStatus.class);

    public final ListPath<com.otaku.otakube.entity.log.SupportLog, com.otaku.otakube.entity.log.QSupportLog> supporterLogs = this.<com.otaku.otakube.entity.log.SupportLog, com.otaku.otakube.entity.log.QSupportLog>createList("supporterLogs", com.otaku.otakube.entity.log.SupportLog.class, com.otaku.otakube.entity.log.QSupportLog.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final ListPath<com.otaku.otakube.entity.log.WishList, com.otaku.otakube.entity.log.QWishList> wishList = this.<com.otaku.otakube.entity.log.WishList, com.otaku.otakube.entity.log.QWishList>createList("wishList", com.otaku.otakube.entity.log.WishList.class, com.otaku.otakube.entity.log.QWishList.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostInspection = inits.isInitialized("hostInspection") ? new QHostInspection(forProperty("hostInspection"), inits.get("hostInspection")) : null;
    }

}

