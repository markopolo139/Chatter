package com.chatter.chatter.app.exceptions;

public class InvalidContentTypeException extends Exception{
    public InvalidContentTypeException() {
        super("Invalid content type of file");
    }
}
