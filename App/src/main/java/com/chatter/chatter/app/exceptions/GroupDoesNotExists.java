package com.chatter.chatter.app.exceptions;

public class GroupDoesNotExists extends Exception {
    public GroupDoesNotExists() {
        super("Invalid group name or admin login");
    }
}
