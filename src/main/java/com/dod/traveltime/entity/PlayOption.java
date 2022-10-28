package com.dod.traveltime.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PlayOption")
@Table(name = "playOption")
public class PlayOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "playId", nullable = false)
    private Long playId;
    @Column(name = "title", nullable = false)
    private String title;
    private String description;
    private int price;
    private String link;
    private String image;
    @Column(name = "playTime", nullable = false)
    private LocalDateTime playTime;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "travelId", referencedColumnName = "travelId")
    private Travel travel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users createUser;
}
