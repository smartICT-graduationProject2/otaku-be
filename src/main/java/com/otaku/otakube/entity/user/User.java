package com.otaku.otakube.entity.user;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    @Column(columnDefinition = "VARCHAR(10)")
    private String name;

    @Column(columnDefinition = "VARCHAR(40)")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(7)")
    private ActiveStatus status;

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.role = Role.ROLE_USER;
        this.status = ActiveStatus.ACTIVE;
    }

    @Builder
    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = ActiveStatus.ACTIVE;
    }
}
