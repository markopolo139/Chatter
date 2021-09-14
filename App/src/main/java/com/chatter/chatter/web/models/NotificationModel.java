package com.chatter.chatter.web.models;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NotificationModel {

    @NotBlank
    public String userFromLogin;

    @Nullable
    public String groupName;

    @NotNull
    public LocalDateTime whenSend;

    public NotificationModel(String userFromLogin, @Nullable String groupName, LocalDateTime whenSend) {
        this.userFromLogin = userFromLogin;
        this.groupName = groupName;
        this.whenSend = whenSend;
    }
}
