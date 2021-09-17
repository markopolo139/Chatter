package com.chatter.chatter.web.models.response;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GroupModel {

    @NotNull
    public Long groupId;

    @NotBlank
    public String groupName;

    @Valid
    public UserModel admin;

    @NotNull
    public int howMuchUsersInGroup;

    public GroupModel(Long groupId, String groupName, UserModel admin, int howMuchUsersInGroup) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.admin = admin;
        this.howMuchUsersInGroup = howMuchUsersInGroup;
    }
}
