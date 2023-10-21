package com.otaku.otakube.entity.log;

import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "t_support_log")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long supportLogId;

    @Column
    private String authUrl;

    @Column
    private String accountHolder;

    @Column
    private Long supportAmount;

    @Column
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User supporter;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "support_id")
    private Support support;

    @Builder
    public SupportLog(String authUrl, String accountHolder, Long supportAmount, User supporter, Support support) {
        this.authUrl = authUrl;
        this.accountHolder = accountHolder;
        this.supportAmount = supportAmount;
        this.supporter = supporter;
        this.support = support;
        this.status = ApprovalStatus.RECEPTION;
    }

    public void approvedSupport(){
        this.status = ApprovalStatus.APPROVED;
    }
}
