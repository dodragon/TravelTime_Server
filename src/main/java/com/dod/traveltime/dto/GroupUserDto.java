package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupUserDto {
    private long groupMemberId;
    private long userIdx;
    private long groupIdx;
}
