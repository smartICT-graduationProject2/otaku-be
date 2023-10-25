package com.otaku.otakube.entity.event;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.log.Approval;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.WishList;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    private List<Approval> approvalList = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    private List<WishList> wishLists = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    private List<EventLog> eventLogs = new ArrayList<>();

    public void saveAdditionalEventInformation(final User hostUser, final Subject eventSubject, final Integer code){
        this.hostUser = hostUser;
        this.subject = eventSubject;
        this.code = code;
    }

    public void saveImageInformation(final String perksImageUrl, final String featuredImageUrl){
        this.perksImage = perksImageUrl;
        this.featuredImage = featuredImageUrl;
    }

    public void completeEventSupport(){
        this.status = EventStatus.PREPARATION;
    }

    public void registerEventSupport(){
        this.status = EventStatus.UNDEFINED;
    }

    @Builder
    public Event(String name, String address, String description, Boolean isPublic, String xNickname, String xId, LocalDate openedDate, LocalDate closedDate) {
        this.name = name; //이벤트 이름
        this.address = address; //이벤트 장소
        this.description = description; //설명
        this.isPublic = isPublic; //공개 여부
        this.xNickname = xNickname; //트위터 닉네임
        this.xId = xId; //트위터 아이디
        this.openedDate = openedDate; //시작
        this.closedDate = closedDate; //마감
        this.status = EventStatus.PREPARATION;
    }
}
