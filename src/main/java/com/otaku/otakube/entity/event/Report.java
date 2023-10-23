package com.otaku.otakube.entity.event;

import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "t_report")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reportId;

    @Column
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Builder
    public Report(Event event) {
        this.event = event;
        this.status = ApprovalStatus.RECEPTION;
    }

    public void approvedReport(){
        this.status = ApprovalStatus.APPROVED;
    }
}
