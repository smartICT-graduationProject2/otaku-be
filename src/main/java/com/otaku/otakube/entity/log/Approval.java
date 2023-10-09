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

@Entity
@Table(name = "t_approval")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Approval extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long approvalId;

    @Column
    private String xNickname;

    @Column
    private String xId;

    @Column
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User applicant;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Builder
    public Approval(String xNickname, String xId, User applicant, Event event) {
        this.xNickname = xNickname;
        this.xId = xId;
        this.applicant = applicant;
        this.event = event;
        this.status = ApprovalStatus.RECEPTION;
    }

    //TODO: 이벤트 로그도 함께 변경
    public void approvedApplicant(){
        this.status = ApprovalStatus.RECEPTION;
    }
}
