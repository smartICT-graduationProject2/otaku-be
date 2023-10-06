package com.otaku.otakube.entity.log;

import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "t_admission")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admission extends BaseTimeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authenticationId;

    @Column
    private String xNickname;

    @Column
    private String xId;

    @Column
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User appliedUser;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Builder
    public Admission(String xNickname, String xId, User appliedUser, Event event) {
        this.xNickname = xNickname;
        this.xId = xId;
        this.appliedUser = appliedUser;
        this.event = event;
    }
}
