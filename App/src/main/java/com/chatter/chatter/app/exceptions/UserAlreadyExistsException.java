package com.chatter.chatter.app.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException() {
        super("User already exists with this data");
    }

}
