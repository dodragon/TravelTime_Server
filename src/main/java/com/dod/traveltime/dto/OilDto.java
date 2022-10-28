package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OilDto {
    private long oilId;
    private long carId;
    private int oilPrice;
    private int moveKm;
    private String createdAt;
}
