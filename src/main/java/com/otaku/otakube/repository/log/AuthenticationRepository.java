package com.otaku.otakube.repository.log;

import com.otaku.otakube.entity.log.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
}
