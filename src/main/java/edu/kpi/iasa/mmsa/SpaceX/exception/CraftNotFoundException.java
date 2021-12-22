package edu.kpi.iasa.mmsa.SpaceX.exception;



public class CraftNotFoundException extends RuntimeException{
    public CraftNotFoundException(){
        super("Spacecraft not found");
    }
}
