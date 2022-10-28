package com.dod.traveltime.service;

import com.dod.traveltime.dto.GroupDto;
import com.dod.traveltime.dto.MessageDto;
import com.dod.traveltime.entity.Group;
import com.dod.traveltime.entity.GroupMember;
import com.dod.traveltime.entity.Users;
import com.dod.traveltime.mapper.GroupMapper;
import com.dod.traveltime.repo.GroupMemberRepository;
import com.dod.traveltime.repo.GroupRepository;
import com.dod.traveltime.repo.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepo;
    private final GroupMemberRepository memberRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepo, GroupMemberRepository memberRepository, UserRepository userRepository) {
        this.groupRepo = groupRepo;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public MessageDto makeGroup(GroupDto dto) {
        Group newGroup = groupRepo.saveAndFlush(new Group(
                        null,
                        dto.getName(),
                        dto.getLeaderId(),
                        dto.getDescription(),
                        null,
                        LocalDateTime.now().toString()
                ));

        MessageDto message = new MessageDto();
        message.setError(false);
        message.setMessage(newGroup.getName() + "이/가 등록되었습니다.");

        memberInsert(newGroup, dto.getLeaderId());

        return message;
    }

    @Transactional
    public List<GroupDto> getMainGroupList(Long idx, Pageable pageable) {
        return GroupMapper.INSTANCE.toGroupDtoList(groupRepo
                .findAllByUsersUserUserIdOrderByCreatedAtDesc(idx, pageable).getContent());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private void memberInsert(Group group, Long userIdx) {
        memberRepository.saveAndFlush(
                new GroupMember(
                        null,
                        userRepository.findById(userIdx).get(),
                        group
                )
        );
    }

    @Transactional
    public GroupDto getGroupFromId(Long idx) {
        Optional<Group> opGroup = groupRepo.findById(idx);
        if(opGroup.isPresent()) {
            return GroupMapper.INSTANCE.toGroupDto(opGroup.get());
        }else {
            return new GroupDto();
        }
    }

    @Transactional
    public int getGroupUserCnt(Long idx) {
        return getGroupFromId(idx).getUsers().size();
    }

    @Transactional
    public MessageDto groupJoin(Long groupId, Long userId) {
        MessageDto message = new MessageDto();

        Optional<Group> opGroup = groupRepo.findById(groupId);
        if(opGroup.isPresent()){
            Group group = opGroup.get();
            Optional<Users> opUser = userRepository.findById(userId);
            if(opUser.isPresent()) {
                if(memberRepository.existsGroupMemberByGroupAndUser(group, opUser.get())){
                    message.setError(true);
                    message.setMessage("이미 참여한 그룹입니다.");
                }else {
                    memberInsert(group, userId);
                    message.setError(false);
                    message.setMessage(group.getName() + "에 참여 완료!");
                }
            }else {
                message.setError(true);
                message.setMessage("계정을 찾을 수 없습니다.");
            }
        }else {
            message.setError(true);
            message.setMessage("그룹을 찾을 수 없습니다.");
        }

        return message;
    }
}
