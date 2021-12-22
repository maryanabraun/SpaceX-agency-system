package edu.kpi.iasa.mmsa.SpaceX.exception;


public class ServiceNotFoundExeption extends RuntimeException{
    public ServiceNotFoundExeption(){
        super("Service not found");
    }
}
