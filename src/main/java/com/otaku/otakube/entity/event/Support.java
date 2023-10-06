package com.otaku.otakube.entity.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_id", updatable = false)
    private Long supportId;

    @Column(name = "target_amount")
    private Long targetAmount;

    @Column(name = "current_amount")
    private Long currentAmount;

    @Column(name = "account_address")
    private String accountAddress;

    @Column(name = "account_holder")
    private String accountHolder;

    @Enumerated(EnumType.STRING)
    private SupportStatus status;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public Support(Long targetAmount, Long currentAmount, String accountAddress, String accountHolder, Event event) {
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.accountAddress = accountAddress;
        this.accountHolder = accountHolder;
        if (targetAmount == 0) {
            this.status = SupportStatus.COMPLETE;
        } else {
            this.status = SupportStatus.IN_PROGRESS;
        }
        this.event = event;
        event.addSupport(this);
    }
}
