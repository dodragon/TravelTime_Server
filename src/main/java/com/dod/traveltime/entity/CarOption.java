package com.dod.traveltime.entity;

import com.dod.traveltime.entity.data.CarType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CarOption")
@Table(name = "carOption")
public class CarOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "carOptionId", nullable = false)
    private Long carOptionId;
    @Column(name = "name", nullable = false)
    private String name;
    private String description;
    private String link;
    private String image;
    private CarType type;
    private int sitEa;
    private int rentPrice;
    private int transPrice;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<OilOption> oil;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "travelId", referencedColumnName = "travelId")
    private Travel travel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users createUser;
}
