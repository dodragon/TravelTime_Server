package com.dod.traveltime.mapper;

import com.dod.traveltime.dto.UserDto;
import com.dod.traveltime.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userId", target = "idx")
    UserDto toDto(Users user);
    List<UserDto> toDtoList(List<Users> users);

    @Mapping(source = "idx", target = "userId")
    Users toEntity(UserDto dto);
    List<Users> toEntityList(List<UserDto> dtoList);
}
