package com.chatter.chatter.app.exceptions;

public class NotAnAdminException extends Exception {
    public NotAnAdminException() {
        super("Selected user is not an Admin of selected group");
    }
}
