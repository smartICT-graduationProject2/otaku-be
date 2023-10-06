package com.otaku.otakube.entity.event;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long eventId;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private Integer code;

    @Column
    private String featuredImage;

    @Column
    private String description;

    @Column
    private Boolean isPublic;

    @Column
    private String xNickname;

    @Column
    private String xId;

    @Column
    private String perksImage;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate openedDate;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate closedDate;

    @Column
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Support support;

    //TODO: 모금 여부에 따라서 status 와 support 지정하기
    @Builder
    public Event(String name, String address, String featuredImage, String description, Boolean isPublic,
                 String xNickname, String xId, String perksImage, LocalDate openedDate, LocalDate closedDate, User user, Subject subject) {
        this.name = name;
        this.address = address;
        this.featuredImage = featuredImage;
        this.description = description;
        this.isPublic = isPublic;
        this.xNickname = xNickname;
        this.xId = xId;
        this.perksImage = perksImage;
        this.openedDate = openedDate;
        this.closedDate = closedDate;
        this.user = user;
        this.subject = subject;
    }
}
