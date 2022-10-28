package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TravelMakeDto {
    private String name;
    private String description;
    private Long groupId;
    private int budget;
    private String startDate;
    private String endDate;
    private List<UserDto> userList;
}
