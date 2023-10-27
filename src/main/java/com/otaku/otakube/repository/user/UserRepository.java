package com.otaku.otakube.repository.user;

import com.otaku.otakube.entity.user.ActiveStatus;
import com.otaku.otakube.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findUserByEmailAndStatus(String email, ActiveStatus status);

    Optional<User> findUserByUserIdAndStatus(Long userId, ActiveStatus status);
}
