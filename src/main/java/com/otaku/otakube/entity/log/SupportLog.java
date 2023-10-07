package com.otaku.otakube.entity.log;

import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.awt.print.Book;
import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "support_log")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_log_id", updatable = false)
    private Long supportLogId;

    @Column(name = "auth_url")
    private String authUrl;

    @Column(name = "support_amount")
    private Long supportAmount;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "supporter_id")
    private User supporter;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "support_id")
    private Support support;

    public SupportLog(User supporter, String authUrl, Long supportAmount, Support support) {
        this.supporter = supporter;
        this.authUrl = authUrl;
        this.supportAmount = supportAmount;
        this.support = support;
    }

    public void changeStatus(Boolean isApproved) {
        if (isApproved) {
            status = ApprovalStatus.APPROVED;
        } else {
            status = ApprovalStatus.RECEPTION;
        }
    }
}
