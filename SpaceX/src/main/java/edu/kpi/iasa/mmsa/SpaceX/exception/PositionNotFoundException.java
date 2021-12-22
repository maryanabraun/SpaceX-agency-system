package edu.kpi.iasa.mmsa.SpaceX.exception;


public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(){
        super("Position not found");
    }
}
