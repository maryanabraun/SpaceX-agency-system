package edu.kpi.iasa.mmsa.SpaceX.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(){
        super("Status not found");
    }
}
