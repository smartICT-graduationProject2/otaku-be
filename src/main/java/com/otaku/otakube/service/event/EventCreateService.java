package com.otaku.otakube.service.event;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.event.EventException;
import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.dto.event.request.EventSaveRequestDto;
import com.otaku.otakube.dto.event.response.EventSaveResponseDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.event.Subject;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.subject.SubjectRepository;
import com.otaku.otakube.service.common.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventCreateService {

    private final EventRepository eventRepository;
    private final SubjectRepository subjectRepository;
    private final AwsS3Service awsS3Service;
    private final AuthInfoHelper authInfoHelper;

    /**
     * 이벤트 등록
     *
     */
    @Transactional
    public EventSaveResponseDto saveEvent(EventSaveRequestDto request, MultipartFile perksImage, MultipartFile featuredImage) {
        String perksImageUrl = awsS3Service.uploadFile(perksImage);
        String featuredImageUrl = awsS3Service.uploadFile(featuredImage);

        User hostUser = authInfoHelper.getUser();

        //임시 입장코드 생성
        Random random = new Random(System.currentTimeMillis());
        final int codeMin = 1000; // 4자리 숫자의 최소값
        final int codeMax = 9999; // 4자리 숫자의 최대값
        Integer code = random.nextInt(codeMax - codeMin + 1) + codeMin;

        //대상 찾기
        Subject eventSubject = subjectRepository.findById(request.subjectId())
                .orElseThrow( () -> EventException.of(ErrorDetails.SUBJECT_NOT_FOUND));

        //이벤트 생성
        Event event = request.toEntity();
        event.saveAdditionalEventInformation(hostUser,eventSubject, code);
        event.saveImageInformation(perksImageUrl, featuredImageUrl);

        //이벤트 저장
        Event createdEvent = eventRepository.save(event);

        return EventSaveResponseDto.builder()
                .eventId(createdEvent.getEventId())
                .build();
    }
}
