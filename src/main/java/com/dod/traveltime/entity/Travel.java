package com.dod.traveltime.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Travel")
@Table(name = "travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "travelId", nullable = false)
    private Long travelId;
    @Column(name = "name", nullable = false)
    private String name;
    private String description;
    private int budget;
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
    private int joinUserEa;
    @Column(name = "startDate", nullable = false)
    private String startDate;
    @Column(name = "endDate", nullable = false)
    private String endDate;
    private Boolean isEnd;
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<TravelNotice> notices;
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<CarOption> cars;
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<EatOption> eats;
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<PlayOption> plays;
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<StayOption> stays;
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
