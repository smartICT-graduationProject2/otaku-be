package com.otaku.otakube.repository.support;

import com.otaku.otakube.entity.log.SupportLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportLogRepository extends JpaRepository<SupportLog, Long> {
}
