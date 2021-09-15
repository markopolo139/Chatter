package com.chatter.chatter.web.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GroupModel {

    @NotNull
    public Long groupId;

    @NotBlank
    public String groupName;

    @NotBlank
    public String adminLogin;

    @NotNull
    public int howMuchUsersInGroup;

    public GroupModel(Long groupId, String groupName, String adminLogin, int howMuchUsersInGroup) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminLogin = adminLogin;
        this.howMuchUsersInGroup = howMuchUsersInGroup;
    }
}
