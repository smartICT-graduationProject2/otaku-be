package com.otaku.otakube.entity.event;

import com.otaku.otakube.entity.log.SupportLog;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "t_support")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long supportId;

    @Column
    private Long targetAmount;

    @Column
    private Long currentAmount;

    @Column
    private String accountAddress;

    @Column
    private String accountHolder;

    @Column
    @Enumerated(EnumType.STRING)
    private SupportStatus status;

    @OneToMany(mappedBy = "support", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    private List<SupportLog> supportLogs;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Builder
    public Support(Long targetAmount, String accountAddress, String accountHolder, Event event) {
        this.targetAmount = targetAmount; //목표 금액
        this.accountAddress = accountAddress; //계좌
        this.accountHolder = accountHolder; //예금주
        this.event = event; //이벤트
        this.status = SupportStatus.IN_PROGRESS;
        this.currentAmount = 0L;
    }

    public boolean updateCurrentAmount(final Long amount){
        this.currentAmount += amount;
        if (currentAmount >= targetAmount){
            this.status = SupportStatus.COMPLETE;
            return true;
        }
        return false;
    }
}
