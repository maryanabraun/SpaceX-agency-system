package edu.kpi.iasa.mmsa.SpaceX.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super("User already exists");
}
}