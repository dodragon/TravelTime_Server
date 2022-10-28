package com.dod.traveltime.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Users")
@Table(name = "users", indexes = {
        @Index(name = "UK_tt_users_email", columnList = "email", unique = true),
        @Index(name = "UK_tt_users_uid", columnList = "uid", unique = true)
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false)
    private Long userId;
    private String uid;
    @Column(name = "name", nullable = false)
    private String name;
    private String email;
    private String password;
    private String profile;
    @CreatedDate
    private String joinedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}