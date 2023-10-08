package com.otaku.otakube.entity.log;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "wish_list")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_list_id", updatable = false)
    private Long wishListId;

    @Enumerated(EnumType.STRING)
    private WishListStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public WishList(User user, Event event) {
        this.status = WishListStatus.ACTIVE;
        this.user = user;
        this.event = event;
    }
}
