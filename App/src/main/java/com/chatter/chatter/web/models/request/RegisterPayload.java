package com.chatter.chatter.web.models.request;

import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterPayload {

    @NotBlank
    @Pattern(regexp = "\\w*")
    public String login;

    @NotBlank
    public String password;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    public RegisterPayload(String login, String password, String email, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
