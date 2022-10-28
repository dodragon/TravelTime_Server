package com.dod.traveltime.mapper;

import com.dod.traveltime.dto.GroupDto;
import com.dod.traveltime.dto.GroupUserDto;
import com.dod.traveltime.entity.Group;
import com.dod.traveltime.entity.GroupMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "groupId", target = "idx")
    GroupDto toGroupDto(Group group);
    List<GroupDto> toGroupDtoList(List<Group> groups);

    @Mapping(source = "idx", target = "groupId")
    Group toGroupEntity(GroupDto dto);
    List<Group> toGroupEntityList(List<GroupDto> groupDtoList);

    @Mapping(source = "groupMemberId", target = "groupMemberId")
    @Mapping(source = "user.userId", target = "userIdx")
    @Mapping(source = "group.groupId", target = "groupIdx")
    GroupUserDto toGuDto(GroupMember groupMember);
    List<GroupUserDto> toGuDtoList(List<GroupMember> groupMembers);
}
