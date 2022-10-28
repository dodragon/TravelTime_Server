package com.dod.traveltime.mapper;

import com.dod.traveltime.dto.*;
import com.dod.traveltime.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TravelMapper {
    TravelMapper INSTANCE = Mappers.getMapper(TravelMapper.class);

    @Mapping(source = "travelId", target = "travelId")
    @Mapping(source = "group.groupId", target = "group.idx")
    @Mapping(source = "group.leaderUserIdx", target = "group.leaderId")
    TravelDto toTravelDto(Travel travel);
    List<TravelDto> toTravelList(List<Travel> travels);

    @Mapping(source = "groupMemberId", target = "groupMemberId")
    @Mapping(source = "user.userId", target = "userIdx")
    @Mapping(source = "group.groupId", target = "groupIdx")
    GroupUserDto toGuDto(GroupMember groupMember);
    List<GroupUserDto> toGuDtoList(List<GroupMember> groupMembers);

    @Mapping(source = "tnId", target = "tnId")
    TravelNoticeDto toNoticeDto(TravelNotice notice);
    List<TravelNoticeDto> toNoticeDtoList(List<TravelNotice> notices);

    @Mapping(source = "tnId", target = "tnId")
    TravelNotice toNoticeEntity(TravelNoticeDto notice);
    List<TravelNotice> toNoticeEntityList(List<TravelNoticeDto> notices);

    @Mapping(source = "carOptionId", target = "carOptionId")
    CarDto toCarDto(CarOption car);
    List<CarDto> toCarDtoList(List<CarOption> carList);

    @Mapping(source = "carOptionId", target = "carOptionId")
    CarOption toCarEntity(CarDto car);
    List<CarOption> toCarEntityList(List<CarDto> carList);

    @Mapping(source = "oilId", target = "oilId")
    OilDto toOilDto(OilOption oil);
    List<OilDto> toOilDtoList(List<OilOption> oilList);

    @Mapping(source = "oilId", target = "oilId")
    OilOption toOilEntity(OilDto oil);
    List<OilOption> toOilEntityList(List<OilDto> oilList);

    @Mapping(source = "eatId", target = "eatId")
    EatDto toEatDto(EatOption eat);
    List<EatDto> toEatDtoList(List<EatOption> eatList);

    @Mapping(source = "eatId", target = "eatId")
    EatOption toEatEntity(EatDto eat);
    List<EatOption> toEatEntityList(List<EatDto> eatList);

    @Mapping(source = "playId", target = "playId")
    PlayDto toPlayDto(PlayOption play);
    List<PlayDto> toPlayDtoList(List<PlayOption> playList);

    @Mapping(source = "playId", target = "playId")
    PlayOption toPlayEntity(PlayDto eat);
    List<PlayOption> toPlayEntityList(List<PlayDto> playList);

    @Mapping(source = "stayId", target = "stayId")
    StayDto toStayDto(StayOption stay);
    List<StayDto> toStayDtoList(List<StayOption> stayList);

    @Mapping(source = "stayId", target = "stayId")
    StayOption toStayEntity(StayDto stay);
    List<StayOption> toStayEntityList(List<StayDto> stayList);
}
