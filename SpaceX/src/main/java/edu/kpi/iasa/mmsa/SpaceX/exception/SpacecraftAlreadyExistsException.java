package edu.kpi.iasa.mmsa.SpaceX.exception;

public class SpacecraftAlreadyExistsException extends RuntimeException{
    public SpacecraftAlreadyExistsException(){
        super("Spacecraft already exists");
    }
}
