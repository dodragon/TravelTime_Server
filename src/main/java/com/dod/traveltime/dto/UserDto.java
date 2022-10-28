package com.dod.traveltime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
    private Date joinedAt;
    private MessageDto message;
}
