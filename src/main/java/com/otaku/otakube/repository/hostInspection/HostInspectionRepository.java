package com.otaku.otakube.repository.hostInspection;

import com.otaku.otakube.dto.hostInspection.response.HostInspectionResponseDto;
import com.otaku.otakube.entity.user.HostInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HostInspectionRepository extends JpaRepository<HostInspection, Long>, HostInspectionRepositoryCustom{
    @Query(value = """
        select new com.otaku.otakube.dto.hostInspection.response.HostInspectionResponseDto(h.hostInspectionId, h.authUrl)
        from HostInspection h
        where h.status = 'RECEPTION'
    """)
    List<HostInspectionResponseDto> findHostInspectionList();
}
