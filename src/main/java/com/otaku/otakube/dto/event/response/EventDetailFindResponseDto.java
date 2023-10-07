package com.otaku.otakube.dto.event.response;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EventDetailFindResponseDto {

    private String featuredImage;
    private String category;
    private LocalDateTime createdAt;
    private String name;
    private String xNickname;
    private String xId;
    private String subject;
    private Long currentAmount;
    private Long targetAmount;
    private Long rate;
    private String description;
    private String address;
    private String accountHolder;
    private String accountAddress;
    private Boolean isPublic;
    private LocalDate openedDate;
    private LocalDate closedDate;

    public EventDetailFindResponseDto(String featuredImage, String category, LocalDateTime createdAt, String name, String xNickname, String xId, String subject, Long currentAmount, Long targetAmount, String description, String address, String accountHolder, String accountAddress, Boolean isPublic, LocalDate openedDate, LocalDate closedDate) {
        this.featuredImage = featuredImage;
        this.category = category;
        this.createdAt = createdAt;
        this.name = name;
        this.xNickname = xNickname;
        this.xId = xId;
        this.subject = subject;
        this.currentAmount = currentAmount;
        this.targetAmount = targetAmount;
        this.rate = (currentAmount / targetAmount) * 100;
        this.description = description;
        this.address = address;
        this.accountHolder = accountHolder;
        this.accountAddress = accountAddress;
        this.isPublic = isPublic;
        this.openedDate = openedDate;
        this.closedDate = closedDate;
    }
}
