package com.otaku.otakube.entity.user;

import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_host_inspection")
public class HostInspection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long hostInspectionId;

    @Column(columnDefinition = "VARCHAR(200)")
    private String authUrl;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(9)")
    private ApprovalStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime modifiedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public HostInspection(String authUrl, User user) {
        this.authUrl = authUrl;
        this.status = ApprovalStatus.RECEPTION;
        this.user = user;
    }

    public void changeStatus(Boolean isApproved) {
        if (isApproved)
            this.status = ApprovalStatus.APPROVED;
        else
            this.status = ApprovalStatus.RECEPTION;
    }
}
