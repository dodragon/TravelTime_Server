package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StayDto {
    private long stayId;
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
    private long travelId;
    private UserDto createUser;
}
