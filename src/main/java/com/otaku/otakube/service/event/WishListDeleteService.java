package com.otaku.otakube.service.event;

import com.otaku.otakube.common.security.helper.AuthInfoHelper;
import com.otaku.otakube.entity.user.User;
import com.otaku.otakube.repository.log.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishListDeleteService {
    private final WishListRepository wishListRepository;
    private final AuthInfoHelper authInfoHelper;

    @Transactional
    public void DeleteWishList(final Long eventId){
        User user = authInfoHelper.getUser();

        wishListRepository.deleteWishListByEventAndUser(eventId, user.getUserId());
    }
}
