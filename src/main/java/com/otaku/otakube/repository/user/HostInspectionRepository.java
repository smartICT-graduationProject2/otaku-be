package com.otaku.otakube.repository.user;

import com.otaku.otakube.dto.host_inspection.response.HostInspectionResponseDto;
import com.otaku.otakube.entity.user.HostInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HostInspectionRepository extends JpaRepository<HostInspection, Long> {
    @Query(value = """
        select new com.otaku.otakube.dto.host_inspection.response.HostInspectionResponseDto(h.hostInspectionId, h.authUrl)
        from HostInspection h
        where h.status = 'RECEPTION'
    """)
    List<HostInspectionResponseDto> findHostInspectionList();
}
