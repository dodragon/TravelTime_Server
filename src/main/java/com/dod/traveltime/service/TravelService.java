package com.dod.traveltime.service;

import com.dod.traveltime.dto.MessageDto;
import com.dod.traveltime.dto.TravelDto;
import com.dod.traveltime.dto.UserDto;
import com.dod.traveltime.entity.Travel;
import com.dod.traveltime.entity.TravelMember;
import com.dod.traveltime.mapper.GroupMapper;
import com.dod.traveltime.mapper.TravelMapper;
import com.dod.traveltime.repo.GroupMemberRepository;
import com.dod.traveltime.repo.TravelRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelService {

    private final TravelRepository travelRepo;
    private final GroupMemberRepository gmRepo;

    public TravelService(TravelRepository travelRepo, GroupMemberRepository gmRepo) {
        this.travelRepo = travelRepo;
        this.gmRepo = gmRepo;
    }

    @Transactional
    public MessageDto makeTravel(TravelDto travelDto) {
        MessageDto message = new MessageDto();
        try {
            Travel travel = travelRepo.saveAndFlush(new Travel(
                    null,
                    travelDto.getName(),
                    travelDto.getDescription(),
                    travelDto.getBudget(),
                    GroupMapper.INSTANCE.toGroupEntity(travelDto.getGroup()),
                    travelDto.getUserList().size(),
                    null,
                    travelDto.getStartDate(),
                    travelDto.getEndDate(),
                    false,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            ));

            List<TravelMember> tmList = new ArrayList<>();
            for(UserDto user: travelDto.getUserList()) {
                tmList.add(userToTm(user, travelDto.getGroup().getIdx(), travel));
            }
            travel.setMemberList(tmList);

            message.setMessage(travelRepo.saveAndFlush(travel).getName()
                    + "이/가 생성되었습니다.");
        }catch (Exception e){
            message.setError(true);
            message.setMessage("오류가 발생했습니다. 다시 시도해 주세요.");
        }

        return message;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private TravelMember userToTm(UserDto user, Long groupId, Travel travel) {
        return new TravelMember(
                null,
                travel,
                gmRepo.findByGroup_GroupIdAndUser_UserId(groupId, user.getIdx()).get()
        );
    }

    @Transactional
    public List<TravelDto> getTravelList(Long groupId, Pageable pageable) {
        return TravelMapper.INSTANCE.toTravelList(travelRepo
                .findAllByGroup_GroupIdOrderByCreatedAtDesc(groupId, pageable).getContent());
    }
}
