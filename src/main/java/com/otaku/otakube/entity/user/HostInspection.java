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

    public void approveHostInspection(){
        this.status = ApprovalStatus.APPROVED;
    }

    public void dismissHostInspection(){
        this.status = ApprovalStatus.DISMISS;
        this.user = null;
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDateTime.now();
    }

    @Builder
    public HostInspection(String authUrl, User user) {
        this.authUrl = authUrl;
        this.status = ApprovalStatus.RECEPTION;
        this.user = user;
    }

}
