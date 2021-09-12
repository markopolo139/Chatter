package com.chatter.chatter.app.exceptions;

import org.springframework.web.multipart.MultipartFile;

public class InvalidContentTypeException extends Exception{
    public InvalidContentTypeException(MultipartFile multipartFile) {
        super("Invalid content type of file: " + multipartFile.getContentType());
    }
}
