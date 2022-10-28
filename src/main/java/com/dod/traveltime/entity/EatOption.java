package com.dod.traveltime.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "EatOption")
@Table(name = "eatOption")
public class EatOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eatId", nullable = false)
    private Long eatId;
    @Column(name = "name", nullable = false)
    private String name;
    private String description;
    private int price;
    private String link;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "travelId", referencedColumnName = "travelId")
    private Travel travel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users createUser;
}
