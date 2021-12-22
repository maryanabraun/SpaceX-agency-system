package edu.kpi.iasa.mmsa.SpaceX.exception;


public class MissionAlreadyExistsException extends RuntimeException{
    public MissionAlreadyExistsException() {
        super("Mission already exists, enter another title");
    }
}
