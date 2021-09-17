package com.chatter.chatter.app.exceptions;

public class InvalidPathException extends Exception{
    public InvalidPathException() {
        super("Invalid path for getting profile photo");
    }
}
