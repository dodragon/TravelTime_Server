package com.dod.traveltime.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "StayOption")
@Table(name = "stayOption")
public class StayOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stayId", nullable = false)
    private Long stayId;
    @Column(name = "name", nullable = false)
    private String name;
    private String description;
    private int price;
    private int addPersonPrice;
    private int subPrice;
    private String link;
    private String image;
    private String location;
    private String startDate;
    private String endDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "travelId", referencedColumnName = "travelId")
    private Travel travel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users createUser;
}
