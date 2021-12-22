package edu.kpi.iasa.mmsa.SpaceX.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "position not found")
public class MissionNotFoundException extends RuntimeException{
    public MissionNotFoundException(){
        super("Mission not found");
    }
}
