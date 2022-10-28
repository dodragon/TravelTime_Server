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
@Entity(name = "OilOption")
@Table(name = "oilOption")
public class OilOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oilId", nullable = false)
    private Long oilId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "carOptionId", referencedColumnName = "carOptionId")
    private CarOption car;
    private int oilPrice;
    private int moveKm;
    @CreatedDate
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
