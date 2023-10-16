package com.otaku.otakube.service.user;

import com.otaku.otakube.dto.host_inspection.response.HostInspectionResponseDto;
import com.otaku.otakube.repository.user.HostInspectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HostInspectionReadService {

    private final HostInspectionRepository hostInspectionRepository;

    public List<HostInspectionResponseDto> getHostInspectionTable(){
        return hostInspectionRepository.findHostInspectionList();
    }

}