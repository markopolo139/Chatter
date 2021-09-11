package com.chatter.chatter.web.models.request;

import javax.validation.constraints.NotBlank;

public class GroupMessagePayload {

    @NotBlank
    public String userFromLogin;

    @NotBlank
    public String groupToAdminLogin;

    @NotBlank
    public String groupToName;

    @NotBlank
    public String content;

    public GroupMessagePayload(String userFromLogin, String groupToAdminLogin, String groupToName, String content) {
        this.userFromLogin = userFromLogin;
        this.groupToAdminLogin = groupToAdminLogin;
        this.groupToName = groupToName;
        this.content = content;
    }
}
