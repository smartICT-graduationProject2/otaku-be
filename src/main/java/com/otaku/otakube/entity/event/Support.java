package com.otaku.otakube.entity.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

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

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Builder
    public Support(Long targetAmount, Long currentAmount, String accountAddress, String accountHolder, Event event) {
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.accountAddress = accountAddress;
        this.accountHolder = accountHolder;
        this.status = SupportStatus.IN_PROGRESS;
        this.event = event;
    }
}
