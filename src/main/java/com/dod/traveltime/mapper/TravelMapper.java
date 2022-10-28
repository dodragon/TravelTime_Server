package com.dod.traveltime.mapper;

import com.dod.traveltime.dto.TravelDto;
import com.dod.traveltime.entity.Travel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TravelMapper {
    TravelMapper INSTANCE = Mappers.getMapper(TravelMapper.class);

    @Mapping(source = "travelId", target = "travelId")
    TravelDto toTravelDto(Travel travel);
    List<TravelDto> toTravelList(List<Travel> travels);
}
