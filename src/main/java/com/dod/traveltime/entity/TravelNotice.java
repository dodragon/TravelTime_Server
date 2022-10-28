package com.dod.traveltime.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TravelNotice")
@Table(name = "travelNotice")
public class TravelNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tnId", nullable = false)
    private Long tnId;
    @Column(name = "title", nullable = false)
    private String title;
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users createUser;
    @CreatedDate
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "travelId", referencedColumnName = "travelId")
    private Travel travel;
}
