package com.chatter.chatter.web.models;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotificationModel {

    @NotBlank
    public String userFromLogin;

    @Nullable
    public String groupName;

    public NotificationModel(String userFromLogin, @Nullable String groupName) {
        this.userFromLogin = userFromLogin;
        this.groupName = groupName;
    }
}
