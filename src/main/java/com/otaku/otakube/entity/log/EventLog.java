package com.otaku.otakube.entity.log;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "t_event_log")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long eventLogId;

    @Column
    @Enumerated(EnumType.STRING)
    private EventLogStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Builder
    public EventLog(EventLogStatus status, User user, Event event) {
        this.status = status;
        this.user = user;
        this.event = event;
    }

    public void approvedEvent(){
        this.status = EventLogStatus.EXPECTED;
    }

    public void participateEvent(){
        this.status = EventLogStatus.ACTIVE;
    }
}
