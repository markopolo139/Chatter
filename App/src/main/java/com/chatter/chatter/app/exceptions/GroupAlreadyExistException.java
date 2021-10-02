package com.chatter.chatter.app.exceptions;

public class GroupAlreadyExistException extends Exception {
    public GroupAlreadyExistException() {
        super("Group with this name already exists");
    }
}
