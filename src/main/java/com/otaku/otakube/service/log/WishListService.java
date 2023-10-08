package com.otaku.otakube.service.log;

import com.otaku.otakube.dto.event.request.WishListRequestDto;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.WishList;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.event.EventRepository;
import com.otaku.otakube.repository.log.WishListRepository;
import com.otaku.otakube.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    /**
     * 관심 이벤트 등록
     */
    public void saveWishList(WishListRequestDto request) {

        User user = userRepository.findById(request.getUserId()).get();
        Event event = eventRepository.findById(request.getEventId()).get();

        WishList wishList = new WishList(user, event);

        wishListRepository.save(wishList);
    }
}
