package com.tcontechco.Tourney.exceptions;

public class ResourceDoesNotExist extends RuntimeException{
    public ResourceDoesNotExist(String message){
        super(message);
    }
}
