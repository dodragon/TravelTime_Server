package com.dod.traveltime.controller.api;

import com.dod.traveltime.dto.TravelDto;
import com.dod.traveltime.service.TravelService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelController {

    private final TravelService service;
    private final Gson gson;

    public TravelController(TravelService service) {
        this.service = service;
        gson = new Gson();
    }

    @PostMapping("travel/insert")
    public String insertTravel(@RequestBody TravelDto dto) {
        return gson.toJson(service.makeTravel(dto));
    }
}
