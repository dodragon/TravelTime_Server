package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PlayDto {
    private long playId;
    private String title;
    private String description;
    private int price;
    private String link;
    private String image;
    private LocalDateTime playTime;
    private long travelId;
    private UserDto createUser;
}
