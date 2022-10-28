package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GroupDto {
    private long idx;
    private String name;
    private long leaderId;
    private String description;
    private List<GroupUserDto> users = new ArrayList<>();
    private String createdAt;
}
