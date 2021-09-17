package com.chatter.chatter.app.exceptions;

public class FolderCreationError extends Exception{
    public FolderCreationError() {
        super("Error occurred during folder creation");
    }
}
