package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TravelDto {
    private long travelId;
    private String name;
    private String description;
    private int budget;
    private GroupDto group;
    private int joinUserEa;
    private String startDate;
    private String endDate;
    private boolean isEnd;
    private List<TravelNoticeDto> noticeList;
    private List<CarDto> carList;
    private List<EatDto> eatList;
    private List<PlayDto> playList;
    private List<StayDto> stayList;
    private String createdAt;
}
