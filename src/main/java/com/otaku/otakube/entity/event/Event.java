package com.otaku.otakube.entity.event;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.log.Approval;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static jakarta.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "t_event")
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

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate openedDate;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate closedDate;

    @Column
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User hostUser;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(mappedBy = "event", fetch = LAZY)
    private Support support;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventLog> eventLogs;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Approval> approvalList;

    @Builder
    public Event(String name, String address, Integer code, String featuredImage, String description, Boolean isPublic, String xNickname, String xId, String perksImage, LocalDate openedDate, LocalDate closedDate, EventStatus status, User hostUser, Subject subject, Support support) {
        this.name = name; //이벤트 이름
        this.address = address; //이벤트 장소
        this.code = code; //입장코드
        this.featuredImage = featuredImage; //대표 이미지
        this.description = description; //설명
        this.isPublic = isPublic; //공개여부
        this.xNickname = xNickname; //트위터 닉네임
        this.xId = xId; //트위터 아이디
        this.perksImage = perksImage; //특전 이미지
        this.openedDate = openedDate; //시작
        this.closedDate = closedDate; //마감
        this.status = status; //상태
        this.hostUser = hostUser; //호스트
        this.subject = subject; //대상
        this.support = support; //서포트, 없으면 널값
    }
}
