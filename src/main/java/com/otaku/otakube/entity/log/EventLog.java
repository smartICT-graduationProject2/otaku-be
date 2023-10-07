package com.otaku.otakube.entity.log;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "event_log")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_log_id", updatable = false)
    private Long eventLogId;

    @Enumerated(EnumType.STRING)
    private EventLogStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;


}
