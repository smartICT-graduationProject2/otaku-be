package com.otaku.otakube.entity.user;

import com.otaku.otakube.entity.common.BaseTimeEntity;
import com.otaku.otakube.entity.event.Event;
import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.SupportLog;
import com.otaku.otakube.entity.log.WishList;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "supporter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupportLog> supporterLogs;

    @OneToMany(mappedBy = "hostUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> eventList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishList> wishList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventLog> eventLogs;


    public void withdrawUser(){
        this.status = ActiveStatus.DELETED;
    }

    public void updateHostRole(){
        this.role = Role.ROLE_HOST;
    }

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
