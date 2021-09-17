package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.services.GroupProfileService;
import com.chatter.chatter.web.models.response.GroupModel;
import com.chatter.chatter.web.models.response.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
public class GroupProfileController {

    @Autowired
    private GroupProfileService mGroupProfileService;

    @GetMapping(value = {"/api/v1/get/user/groups/{login}/{pattern}", "/api/v1/get/user/groups/{login}"})
    public List<GroupModel> getGroupsWhereIsUser(
            @PathVariable(name = "login") @Valid @NotBlank String login,
            @PathVariable(name = "pattern", required = false) String pattern
    ) {
        if (pattern == null)
            pattern = "";
        return mGroupProfileService.getGroupsWhereIsSelectedUser(login, pattern);
    }

    @GetMapping("/api/v1/get/group/users/{groupId}")
    public List<UserModel> getUsersInGroup(
            @PathVariable(name = "groupId") @Valid @NotNull Long groupId

    ) {
        return mGroupProfileService.getUsersInGroup(groupId);
    }


}
