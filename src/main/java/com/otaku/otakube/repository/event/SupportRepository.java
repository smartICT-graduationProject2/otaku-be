package com.otaku.otakube.repository.event;

import com.otaku.otakube.entity.event.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support, Long> {
}
