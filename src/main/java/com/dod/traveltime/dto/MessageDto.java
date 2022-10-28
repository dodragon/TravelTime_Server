package com.dod.traveltime.dto;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class MessageDto {
    private boolean isError = false;
    private String message;
}
