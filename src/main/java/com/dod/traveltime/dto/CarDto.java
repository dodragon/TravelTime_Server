package com.dod.traveltime.dto;

import com.dod.traveltime.entity.data.CarType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CarDto {
    private long carOptionId;
    private String name;
    private String description;
    private String link;
    private String image;
    private CarType type;
    private int sitEa;
    private int rentPrice;
    private int transPrice;
    private List<OilDto> oil;
    private long travelId;
    private UserDto createUser;
}
