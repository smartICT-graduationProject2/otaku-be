package com.otaku.otakube.repository.log;

import com.otaku.otakube.entity.log.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    @Modifying
    @Query("delete from WishList w where w.event.eventId = :eventId AND w.user.userId = :userId")
    void deleteWishListByEventAndUser(@Param("eventId") final Long eventId, @Param("userId") final Long userId);
}
