package com.dod.traveltime.service;

import com.dod.traveltime.dto.MessageDto;
import com.dod.traveltime.dto.TravelDto;
import com.dod.traveltime.dto.TravelMakeDto;
import com.dod.traveltime.dto.UserDto;
import com.dod.traveltime.entity.Group;
import com.dod.traveltime.entity.Travel;
import com.dod.traveltime.entity.TravelMember;
import com.dod.traveltime.mapper.TravelMapper;
import com.dod.traveltime.mapper.UserMapper;
import com.dod.traveltime.repo.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class TravelService {

    private final TravelRepository travelRepo;
    private final GroupMemberRepository gmRepo;
    private final GroupRepository groupRepo;
    private final TravelMemberRepository tmRepo;
    private final UserRepository userRepo;

    public TravelService(TravelRepository travelRepo, GroupMemberRepository gmRepo,
                         GroupRepository groupRepo, TravelMemberRepository tmRepo, UserRepository userRepo) {
        this.travelRepo = travelRepo;
        this.gmRepo = gmRepo;
        this.groupRepo = groupRepo;
        this.tmRepo = tmRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public MessageDto makeTravel(TravelMakeDto travelDto) {
        MessageDto message = new MessageDto();
        try {
            Optional<Group> opGroup = groupRepo.findById(travelDto.getGroupId());
            if(opGroup.isPresent()){
                Travel travel = travelRepo.saveAndFlush(new Travel(
                        null,
                        travelDto.getName(),
                        travelDto.getDescription(),
                        travelDto.getBudget(),
                        opGroup.get(),
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
                        LocalDateTime.now().toString()
                ));

                List<TravelMember> tmList = new ArrayList<>();
                for(UserDto user: travelDto.getUserList()) {
                    tmList.add(tmRepo.saveAndFlush(userToTm(user, opGroup.get().getGroupId(), travel)));
                }
                travel.setMemberList(tmList);

                message.setMessage(travelRepo.saveAndFlush(travel).getName()
                        + "이/가 생성되었습니다.");
            }else {
                message.setError(true);
                message.setMessage("그룹을 찾을 수 없습니다.");
            }
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
        List<TravelDto> travelList = TravelMapper.INSTANCE.toTravelList(travelRepo
                .findAllByGroup_GroupIdOrderByCreatedAtDesc(groupId, pageable).getContent());

        for(TravelDto td : travelList) {
            List<TravelMember> tmList = tmRepo.findAllByTravel_TravelId(td.getTravelId()).get();
            List<UserDto> userList = new ArrayList<>();
            for(TravelMember tm : tmList) {
                userList.add(UserMapper.INSTANCE.toDto(
                        userRepo.findById(tm.getGm().getUser().getUserId()).get()
                ));
            }

            td.setUserList(userList);
        }

        return travelList;
    }
}
