package com.dod.traveltime.service;

import com.dod.traveltime.dto.GroupDto;
import com.dod.traveltime.dto.MessageDto;
import com.dod.traveltime.dto.TravelDto;
import com.dod.traveltime.entity.Travel;
import com.dod.traveltime.mapper.GroupMapper;
import com.dod.traveltime.repo.TravelRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelService {

    private final TravelRepository travelRepo;

    public TravelService(TravelRepository travelRepo) {
        this.travelRepo = travelRepo;
    }

    public MessageDto makeTravel(TravelDto travelDto) {
        MessageDto message = new MessageDto();
        try {
            message.setMessage(travelRepo.saveAndFlush(new Travel(
                    null,
                    travelDto.getName(),
                    travelDto.getDescription(),
                    travelDto.getBudget(),
                    GroupMapper.INSTANCE.toGroupEntity(travelDto.getGroup()),
                    travelDto.getJoinUserEa(),
                    travelDto.getStartDate(),
                    travelDto.getEndDate(),
                    false,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )).getName() + "이/가 생성되었습니다.");
        }catch (Exception e){
            message.setError(true);
            message.setMessage("오류가 발생했습니다. 다시 시도해 주세요.");
        }

        return message;
    }
}
