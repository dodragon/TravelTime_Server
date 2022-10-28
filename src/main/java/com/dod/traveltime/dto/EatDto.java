package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EatDto {
    private long eatId;
    private String name;
    private String description;
    private int price;
    private String link;
    private String image;
    private long travelId;
    private UserDto createUser;
}
