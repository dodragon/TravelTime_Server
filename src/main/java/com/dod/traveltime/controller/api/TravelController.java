package com.dod.traveltime.controller.api;

import com.dod.traveltime.dto.TravelDto;
import com.dod.traveltime.dto.TravelMakeDto;
import com.dod.traveltime.service.TravelService;
import com.google.gson.Gson;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TravelController {

    private final TravelService service;
    private final Gson gson;

    public TravelController(TravelService service) {
        this.service = service;
        gson = new Gson();
    }

    @PostMapping("travel/insert")
    public String insertTravel(@RequestBody TravelMakeDto dto) {
        return gson.toJson(service.makeTravel(dto));
    }

    @GetMapping("travel/list")
    public String travelList(@RequestParam("groupId") Long groupId, Pageable pageable) {
        List<TravelDto> data = service.getTravelList(groupId, pageable);
        return gson.toJson(data);
    }
}
