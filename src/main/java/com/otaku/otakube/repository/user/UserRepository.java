package com.otaku.otakube.repository.user;

import com.otaku.otakube.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
