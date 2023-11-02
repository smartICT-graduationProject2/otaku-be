package com.otaku.otakube.repository.support;

import com.otaku.otakube.dto.support.response.SupportInfoResponseDto;
import com.otaku.otakube.entity.event.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupportRepository extends JpaRepository<Support, Long> {
    @Query("""
           select new com.otaku.otakube.dto.support.response.SupportInfoResponseDto(
                s.supportId,
                s.bank,
                s.accountAddress,
                s.accountHolder
           )
           from Support as s
           where s.supportId = ?1
    """)
    Optional<SupportInfoResponseDto> findSupportInfoById(Long supportId);
}
