package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private long idx;
    private String uid;
    private String name;
    private String email;
    private String password;
    private String profile;
    private String joinedAt;
    private MessageDto message;
}
