package com.otaku.otakube.entity.event;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Random;

import static jakarta.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", updatable = false)
    private Long eventId;

    private String name;

    private String address;

    private Integer code;

    @Column(name = "featured_image")
    private String featuredImage;

    private String description;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "x_nickname")
    private String xNickname;

    @Column(name = "x_id")
    private String xId;

    @Column(name = "perks_image")
    private String perksImage;

    @Temporal(TemporalType.DATE)
    @Column(name = "opened_date")
    private LocalDate openedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "closed_date")
    private LocalDate closedDate;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(mappedBy = "event", fetch = LAZY)
    private Support support;

    @Builder
    public Event(String name, String address, String featuredImage, String description, Boolean isPublic, String xNickname, String xId, String perksImage, LocalDate openedDate, LocalDate closedDate, User user, Subject subject) {
        this.name = name;
        this.address = address;
        this.featuredImage = featuredImage;
        this.description = description;
        this.isPublic = isPublic;
        if (isPublic) {
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());
            this.code = random.nextInt(10000);
        }
        this.xNickname = xNickname;
        this.xId = xId;
        this.perksImage = perksImage;
        this.openedDate = openedDate;
        this.closedDate = closedDate;
        this.status = EventStatus.PREPARATION;
        this.user = user;
        this.subject = subject;
//        user.getEvents().add(this); //User의 events에 event 추가
    }

    public void addSupport(Support support) {
        this.support = support;
    }
}
