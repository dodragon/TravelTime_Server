package com.dod.traveltime.service;

import com.dod.traveltime.dto.MessageDto;
import com.dod.traveltime.dto.UserDto;
import com.dod.traveltime.entity.Users;
import com.dod.traveltime.mapper.UserMapper;
import com.dod.traveltime.repo.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public MessageDto joinUser(UserDto dto) {
        MessageDto message = new MessageDto();
        try{
            message.setMessage(userRepo.saveAndFlush(new Users(
                    null,
                    dto.getUid(),
                    dto.getName(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getProfile(),
                    new Date()
            )).getName() + "님 가입을 축하합니다.\n가입하신 계정으로 로그인해주세요.");
        }catch (Exception e) {
            message.setError(true);
            if(e.getMessage().contains("UK_tt_users_email")){
                message.setMessage("이미 존재하는 이메일입니다.");
            }else if(e.getMessage().contains("UK_tt_users_uid")) {
                message.setMessage("이미 가입하신 계정입니다.");
            }else {
                message.setMessage("죄송합니다. 오류가 발생했습니다.");
            }
        }

        return message;
    }

    public UserDto findUserForLogin(String email, String pw, String uid) {
        UserDto dto = new UserDto();
        Optional<Users> opUser = userRepo.findByEmailAndUidAndPassword(email, uid, pw);

        if(opUser.isPresent()){
            dto = UserMapper.INSTANCE.toDto(opUser.get());
            dto.setMessage(new MessageDto());
        }else {
            MessageDto mDto = new MessageDto();
            mDto.setError(true);
            mDto.setMessage("존재하지 않는 계정입니다.");
            dto.setMessage(mDto);
        }

        return dto;
    }

    public UserDto findUserForSnsLogin(String email, String uid, String name) {
        UserDto dto = new UserDto();
        Optional<Users> opUser = userRepo.findByEmailAndUid(email, uid);

        if(opUser.isPresent()) {
            dto = UserMapper.INSTANCE.toDto(opUser.get());
            dto.setMessage(new MessageDto());
        }else {
            dto.setEmail(email);
            dto.setUid(uid);
            dto.setPassword("");
            dto.setName(name);
            dto.setProfile("");
            dto.setMessage(joinUser(dto));
        }

        return dto;
    }

    public List<UserDto> findGroupUserList(Long idx, Pageable pageable) {
        return UserMapper.INSTANCE.toDtoList(
                userRepo.findAllByGroupUsers(idx, pageable).getContent());
    }

    public UserDto updateUser(Long userId, UserDto data) {
        MessageDto message = new MessageDto();

        Optional<Users> opUser = userRepo.findById(userId);
        if(opUser.isPresent()) {
            Users oldUser = opUser.get();
            Users newUser = new Users(
                    oldUser.getUserId(),
                    oldUser.getUid(),
                    data.getName(),
                    oldUser.getEmail(),
                    data.getPassword(),
                    data.getProfile(),
                    oldUser.getJoinedAt()
            );

            UserDto newDto = UserMapper.INSTANCE.toDto(userRepo.saveAndFlush(newUser));
            message.setMessage("수정이 완료되었습니다.");
            newDto.setMessage(message);
            return newDto;
        }else {
            message.setError(true);
            message.setMessage("계정을 찾을 수 없습니다.");
            data.setMessage(message);
            return data;
        }
    }
}
