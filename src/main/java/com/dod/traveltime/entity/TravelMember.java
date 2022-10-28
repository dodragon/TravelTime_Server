package com.dod.traveltime.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TravelMember")
@Table(name = "travelMember")
public class TravelMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tmId", nullable = false)
    private Long tmId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "travelId", referencedColumnName = "travelId")
    private Travel travel;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupMemberId", referencedColumnName = "groupMemberId")
    private GroupMember gm;
}
