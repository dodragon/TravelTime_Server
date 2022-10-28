package com.dod.traveltime.controller.api;

import com.dod.traveltime.dto.GroupDto;
import com.dod.traveltime.service.GroupService;
import com.google.gson.Gson;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {

    private final GroupService groupService;
    private final Gson gson;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
        gson = new Gson();
    }

    @PostMapping("group/insert")
    public String groupInsert(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("userIdx") Long userIdx) {
        GroupDto dto = new GroupDto();
        dto.setName(name);
        dto.setDescription(description);
        dto.setLeaderId(userIdx);
        return gson.toJson(groupService.makeGroup(dto));
    }

    @GetMapping("group/list")
    public String groupList(@RequestParam("userIdx") Long userIdx, Pageable pageable) {
        return gson.toJson(groupService.getMainGroupList(userIdx, pageable));
    }

    @GetMapping("group/userCnt")
    public String groupUserCnt(@RequestParam("groupId") Long idx) {
        return gson.toJson(groupService.getGroupUserCnt(idx));
    }

    @PostMapping("group/join")
    public String groupJoin(@RequestParam("groupId") Long gid, @RequestParam("userId") Long uid) {
        return gson.toJson(groupService.groupJoin(gid, uid));
    }
}
