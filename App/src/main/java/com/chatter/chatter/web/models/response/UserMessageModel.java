package com.chatter.chatter.web.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserMessageModel {

    @NotBlank
    public String userFromLogin;

    @NotBlank
    public String userToLogin;

    @NotBlank
    public String content;

    @NotBlank
    public String status;

    @NotNull
    public LocalDateTime whenSend;

    public UserMessageModel(
            String userFromLogin, String userToLogin, String content, String status, LocalDateTime whenSend
    ) {
        this.userFromLogin = userFromLogin;
        this.userToLogin = userToLogin;
        this.content = content;
        this.status = status;
        this.whenSend = whenSend;
    }
}
