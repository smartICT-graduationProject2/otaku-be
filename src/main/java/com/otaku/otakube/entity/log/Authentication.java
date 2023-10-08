package com.otaku.otakube.entity.log;

import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authentication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authentication_id", updatable = false)
    private Long authenticationId;

    @Column(name = "x_nickname")
    private String xNickname;

    @Column(name = "x_id")
    private String xId;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public Authentication(String xNickname, String xId, User user, Event event) {
        this.xNickname = xNickname;
        this.xId = xId;
        this.user = user;
        this.event = event;
    }

    public void changeStatus(Boolean isApproved) {

        if (isApproved) {
            this.status = ApprovalStatus.APPROVED;
        } else {
            this.status = ApprovalStatus.RECEPTION;
        }
    }
}
