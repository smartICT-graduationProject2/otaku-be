package com.otaku.otakube.repository.log;

import com.otaku.otakube.entity.log.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    //userId 관심 이벤트 조회
    @Query("select w.event.eventId from WishList w where w.user.userId = :userId")
    List<Long> findWishEvents(@Param("userId") Long userId);
}
