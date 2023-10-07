package com.otaku.otakube.dto.event.request;

import lombok.Getter;

/**
 * 이벤트 저장 조건
 */
@Getter
public class EventSaveRequestDto {

    private Long userId;
    private Boolean isPublic;
    private String xNickname;
    private String xId;
    private String name;
    private String subject;
    private String category;
    private String openedDate; // 0000-00-00
    private String closedDate; // 0000-00-00
    private String address;
    private String perksImage;
    private Long targetAmount;
    private String accountHolder;
    private String accountAddress;
    private String description;
    private String featuredImage;

    public EventSaveRequestDto(Long userId, Boolean isPublic, String xNickname, String xId, String name, String subject, String category, String openedDate, String closedDate, String address, String perksImage, Long targetAmount, String accountHolder, String accountAddress, String description, String featuredImage) {
        this.userId = userId;
        this.isPublic = isPublic;
        this.xNickname = xNickname;
        this.xId = xId;
        this.name = name;
        this.subject = subject;
        this.category = category;
        this.openedDate = openedDate;
        this.closedDate = closedDate;
        this.address = address;
        this.perksImage = perksImage;
        this.targetAmount = targetAmount;
        this.accountHolder = accountHolder;
        this.accountAddress = accountAddress;
        this.description = description;
        this.featuredImage = featuredImage;
    }
}
