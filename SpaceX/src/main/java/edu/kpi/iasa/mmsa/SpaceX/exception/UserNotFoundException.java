package edu.kpi.iasa.mmsa.SpaceX.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not found");
    }
}
