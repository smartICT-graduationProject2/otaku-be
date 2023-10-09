package com.otaku.otakube.entity.log;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWishList is a Querydsl query type for WishList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWishList extends EntityPathBase<WishList> {

    private static final long serialVersionUID = 193583840L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWishList wishList = new QWishList("wishList");

    public final com.otaku.otakube.entity.common.QBaseTimeEntity _super = new com.otaku.otakube.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.otaku.otakube.entity.event.QEvent event;

    public final EnumPath<WishListStatus> status = createEnum("status", WishListStatus.class);

    public final com.otaku.otakube.entity.user.QUser user;

    public final NumberPath<Long> wishListId = createNumber("wishListId", Long.class);

    public QWishList(String variable) {
        this(WishList.class, forVariable(variable), INITS);
    }

    public QWishList(Path<? extends WishList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWishList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWishList(PathMetadata metadata, PathInits inits) {
        this(WishList.class, metadata, inits);
    }

    public QWishList(Class<? extends WishList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new com.otaku.otakube.entity.event.QEvent(forProperty("event"), inits.get("event")) : null;
        this.user = inits.isInitialized("user") ? new com.otaku.otakube.entity.user.QUser(forProperty("user")) : null;
    }

}

