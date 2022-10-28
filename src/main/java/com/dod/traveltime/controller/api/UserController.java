package com.dod.traveltime.controller.api;

import com.dod.traveltime.dto.UserDto;
import com.dod.traveltime.service.UserService;
import com.google.gson.Gson;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;
    private final Gson gson;

    public UserController(UserService service) {
        this.service = service;
        gson = new Gson();
    }

    @PostMapping("user/join")
    public String userJoin(@RequestBody UserDto dto) {
        return gson.toJson(service.joinUser(dto));
    }

    @PostMapping("user/login")
    public String userLogin(@RequestParam("email") String email, @RequestParam("password")
                            String pw, @RequestParam("uid") String uid){
        return gson.toJson(service.findUserForLogin(email, pw, uid));
    }

    @PostMapping("user/snslogin")
    public String snsLogin(@RequestParam("email") String email, @RequestParam("uid") String uid,
                           @RequestParam("name") String name){
        return gson.toJson(service.findUserForSnsLogin(email, uid, name));
    }

    @GetMapping("user/groupList")
    public String groupUsers(@RequestParam("idx") Long idx, Pageable pageable) {
        return gson.toJson(service.findGroupUserList(idx, pageable));
    }

    @PutMapping("user/update/{userId}")
    public String userUpdate(@PathVariable(name = "userId")Long userId, @RequestBody UserDto dto) {
        return gson.toJson(service.updateUser(userId, dto));
    }
}
