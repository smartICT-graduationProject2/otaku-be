package com.otaku.otakube.repository.log;

import com.otaku.otakube.entity.log.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}
