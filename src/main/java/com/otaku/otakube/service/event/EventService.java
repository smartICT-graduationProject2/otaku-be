package com.otaku.otakube.service.event;

import com.otaku.otakube.dto.event.request.EventFindRequestDto;
import com.otaku.otakube.dto.event.request.EventSaveRequestDto;
import com.otaku.otakube.dto.event.response.*;
import com.otaku.otakube.entity.common.ApprovalStatus;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Report;
import com.otaku.otakube.entity.event.Subject;
import com.otaku.otakube.entity.event.Support;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.event.ReportRepository;
import com.otaku.otakube.repository.event.SubjectRepository;
import com.otaku.otakube.repository.event.SupportRepository;
import com.otaku.otakube.repository.log.EventLogRepository;
import com.otaku.otakube.repository.log.WishListRepository;
import com.otaku.otakube.repository.user.HostInspectionRepository;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final WishListRepository wishListRepository;
    private final SubjectRepository subjectRepository;
    private final SupportRepository supportRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final EventLogRepository eventLogRepository;
    private final HostInspectionRepository hostInspectionRepository;

    /**
     * 이벤트 조회
     * userId=1 -> user_id 1이 현재 이용중
     * isWishList=false -> 오늘의 이벤트, isWishList=true -> 현재 user_id의 관심 이벤트
     * subject=백현 -> 주인공 이름이 "백현"을 포함하는 이벤트들을 조회
     */
    public List<EventFindResponseDto> findEvents(EventFindRequestDto request) {

        List<EventFindResponseDto> todayEvents = eventRepository.findTodayEvents();
        List<Long> wishEventIds = wishListRepository.findWishEvents(request.getUserId());

        //관심 이벤트 true로 설정
        for (EventFindResponseDto todayEvent : todayEvents) {
            if (wishEventIds.contains(todayEvent.getEventId())) {
                todayEvent.setIsWishList(true);
            }
        }

        if (!(request.getSubject() == null)) { //검색 이벤트

            List<EventFindResponseDto> searchEvents = new ArrayList<>();

            for (EventFindResponseDto todayEvent : todayEvents) {
                if (todayEvent.getSubject().contains(request.getSubject())) {
                    searchEvents.add(todayEvent);
                }
            }

            return searchEvents;

        } else if (request.getIsWishList()) { //관심 이벤트

            List<EventFindResponseDto> wishEvents = new ArrayList<>();

            for (EventFindResponseDto todayEvent : todayEvents) {
                if (todayEvent.getIsWishList()) {
                    wishEvents.add(todayEvent);
                }
            }

            return wishEvents;
        }

        return todayEvents; //오늘의 이벤트

    }

    /**
     * 이벤트 등록
     */
    @Transactional
    public void saveEvent(EventSaveRequestDto request) {

        //해당 카테고리가 이미 있는지 검사
        Long subjectId = null;
        for (Subject subject : subjectRepository.findAll()) {
            if (subject.getName().equals(request.getSubject())) {
                subjectId = subject.getSubjectId();
            }
        }

        Subject subject;
        if (subjectId != null) { //카테고리에 이미 있다면 있는거 사용
            subject = subjectRepository.findById(subjectId).get();
        } else { //없다면 새롭게 카테고리 만듦
            subject = new Subject(request.getCategory(), request.getSubject());
            subjectRepository.save(subject);
        }

        User user = userRepository.findById(request.getUserId()).get();
        Event event = new Event(request.getName(), request.getAddress(), request.getFeaturedImage(), request.getDescription(), request.getIsPublic(), request.getXNickname(), request.getXId(), request.getPerksImage(), LocalDate.parse(request.getOpenedDate()), LocalDate.parse(request.getClosedDate()), user, subject);
        eventRepository.save(event);

        Support support = new Support(request.getTargetAmount(), 0L, request.getAccountAddress(), request.getAccountHolder(), event);
        supportRepository.save(support);
    }

    /**
     * 이벤트 상세 조회
     */
    public EventDetailFindResponseDto findEventDetail(Long eventId) {

        Event event = eventRepository.findById(eventId).get();
        Subject subjectTable = event.getSubject();
        Support support = event.getSupport();

        String featuredImage = event.getFeaturedImage();
        String category = subjectTable.getCategory();
        LocalDateTime createdAt = event.getCreatedAt();
        String name = event.getName();
        String xNickname = event.getXNickname();
        String xId = event.getXId();
        String subject = subjectTable.getName();
        Long currentAmount = support.getCurrentAmount();
        Long targetAmount = support.getTargetAmount();
        String description = event.getDescription();
        String address = event.getAddress();
        String accountHolder = support.getAccountHolder();
        String accountAddress = support.getAccountAddress();
        Boolean isPublic = event.getIsPublic();
        LocalDate openedDate = event.getOpenedDate();
        LocalDate closedDate = event.getClosedDate();

        return new EventDetailFindResponseDto(featuredImage, category, createdAt, name, xNickname,
                xId, subject, currentAmount, targetAmount, description, address, accountHolder,
                accountAddress, isPublic, openedDate, closedDate);
    }

    /**
     * 이벤트 신고
     */
    @Transactional
    public void reportEvent(Long eventId) {

        Event event = eventRepository.findById(eventId).get();

        reportRepository.save(new Report(event));
    }

    /**
     * 입장권 조회
     */
    public AdmissionResponseDto findAdmission(Long eventId) {

        Event event = eventRepository.findById(eventId).get();

        return new AdmissionResponseDto(event.getName(), event.getCreatedAt(), event.getXNickname());
    }

    /**
     * 이벤트 특전 이미지 조회
     */
    public String findPerksImage(Long eventId) {
        return eventRepository.findById(eventId).get().getPerksImage();
    }

    /**
     * 개최자의 이벤트 전체 조회
     */
    public List<HostEventsFindResponseDto> findHostEvents(Long userId) {
        return eventRepository.findHostEvents(userId);
    }

    /**
     * 마이페이지 조회
     */
    public MyPageResponseDto findMyPage(Long userId) {
        User user = userRepository.findById(userId).get();

        List<MyPageEventsResponseDto> myPageEvents = eventLogRepository.findMyPageEvents(userId);

        List<MyPageEventsResponseDto> myPagePerks = eventLogRepository.findMyPagePerks(userId);

        ApprovalStatus hostStatus = hostInspectionRepository.findHostStatus(userId);

        Boolean isHost = false;
        if (hostStatus != null && hostStatus.equals(ApprovalStatus.APPROVED))
            isHost = true;

        return new MyPageResponseDto(user.getName(), myPageEvents, myPagePerks, isHost);
    }
}
