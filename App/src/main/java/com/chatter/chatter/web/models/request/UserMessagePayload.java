package com.chatter.chatter.web.models.request;

import javax.validation.constraints.NotBlank;

public class UserMessagePayload {

    @NotBlank
    public String userFromLogin;

    @NotBlank
    public String userToLogin;

    @NotBlank
    public String content;

    public UserMessagePayload(String userFromLogin, String userToLogin, String content) {
        this.userFromLogin = userFromLogin;
        this.userToLogin = userToLogin;
        this.content = content;
    }
}
