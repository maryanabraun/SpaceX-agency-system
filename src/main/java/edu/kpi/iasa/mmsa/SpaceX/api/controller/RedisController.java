package edu.kpi.iasa.mmsa.SpaceX.api.controller;

import edu.kpi.iasa.mmsa.SpaceX.api.dto.RedisDto;
import edu.kpi.iasa.mmsa.SpaceX.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/redis")
public class RedisController {
    private final RedisService redisService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveValue(@RequestBody RedisDto redisDto){
        return ResponseEntity.ok(redisService.save(redisDto.getValue(), redisDto.getKey()));
    }
}
