package com.dod.traveltime.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Group")
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupId", nullable = false)
    private Long groupId;
    @Column(name = "name", nullable = false)
    private String name;
    private Long leaderUserIdx;
    private String description;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupMember> users = new java.util.ArrayList<>();
    @CreatedDate
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}