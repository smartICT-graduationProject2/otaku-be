package com.otaku.otakube.service.event;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.WishList;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.log.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishListCreateService {
    private final WishListRepository wishListRepository;
    private final EventReadService eventReadService;
    private final AuthInfoHelper authInfoHelper;

    @Transactional
    public void CreateWishList(final Long eventId){
        Event eventForCreatingWishList = eventReadService.findEventByIdAndStatus(eventId);
        User user = authInfoHelper.getUser();

        wishListRepository.save(
                WishList.builder()
                        .event(eventForCreatingWishList)
                        .user(user)
                        .build()
        );
    }
}
