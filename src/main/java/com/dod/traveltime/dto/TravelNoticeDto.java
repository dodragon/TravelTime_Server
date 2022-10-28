package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TravelNoticeDto {
    private long tnId;
    private String title;
    private String content;
    private UserDto createUser;
    private String createdAt;
    private long travelId;
}
