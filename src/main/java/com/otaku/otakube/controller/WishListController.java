package com.otaku.otakube.controller;

import com.otaku.otakube.dto.event.request.WishListRequestDto;
import com.otaku.otakube.service.log.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wish-lists")
public class WishListController {

    private final WishListService wishListService;

    //관심 이벤트 등록
    @PostMapping("")
    public void saveWishList(WishListRequestDto request) {
        wishListService.saveWishList(request);
    }
}
