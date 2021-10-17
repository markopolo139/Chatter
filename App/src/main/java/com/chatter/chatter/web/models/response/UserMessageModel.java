package com.chatter.chatter.web.models.response;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserMessageModel {

    public Long messageId;

    @NotBlank
    public String userFromLogin;

    @NotBlank
    public String userToLogin;

    @NotBlank
    public String content;

    @NotBlank
    public String status;

    @Nullable
    public LocalDateTime whenSend;

    public UserMessageModel(
            String userFromLogin, String userToLogin, String content,
            String status, LocalDateTime whenSend, Long messageId
    ) {
        this.userFromLogin = userFromLogin;
        this.userToLogin = userToLogin;
        this.content = content;
        this.status = status;
        this.whenSend = whenSend;
        this.messageId = messageId;
    }
}
