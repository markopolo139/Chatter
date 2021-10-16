package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.exceptions.GroupAlreadyExistException;
import com.chatter.chatter.app.exceptions.GroupDoesNotExists;
import com.chatter.chatter.app.exceptions.InvalidUserExceptions;
import com.chatter.chatter.app.exceptions.NotAnAdminException;
import com.chatter.chatter.app.services.GroupManagementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@CrossOrigin
@Validated
public class GroupManagementsController {

    @Autowired
    private GroupManagementsService mGroupManagementsService;

    @PostMapping("/api/v1/group/create/{groupName}")
    public void createGroup(
            @Valid @Pattern(regexp = "\\w+") @PathVariable("groupName") String groupName
    ) throws GroupAlreadyExistException {
        mGroupManagementsService.createGroup(groupName);
    }

    @PutMapping("/api/v1/group/add/user/{groupName}/{adminLogin}/{addUserLogin}")
    public void addUser(
            @Valid @NotBlank @PathVariable("groupName") String groupName,
            @Valid @NotBlank @PathVariable("adminLogin") String adminLogin,
            @Valid @NotBlank @PathVariable("addUserLogin") String addUserLogin
    ) throws GroupDoesNotExists, InvalidUserExceptions {
        mGroupManagementsService.addUser(groupName, adminLogin, addUserLogin);
    }

    @PutMapping("/api/v1/group/delete/user/{groupName}/{deleteUserLogin}")
    public void deleteUser(
            @Valid @NotBlank @PathVariable("groupName") String groupName,
            @Valid @NotBlank @PathVariable("deleteUserLogin") String deleteUserLogin
    ) throws NotAnAdminException, InvalidUserExceptions {
        mGroupManagementsService.deleteUser(groupName, deleteUserLogin);
    }

    @PutMapping("/api/v1/group/leave/{groupName}/{adminLogin}")
    public void leaveGroup(
            @Valid @NotBlank @PathVariable("groupName") String groupName,
            @Valid @NotBlank @PathVariable("adminLogin") String adminLogin
    ) throws InvalidUserExceptions, GroupDoesNotExists, NotAnAdminException {
        mGroupManagementsService.leaveGroup(groupName, adminLogin);
    }

    @DeleteMapping("/api/v1/group/delete/{groupName}/{adminLogin}")
    public void deleteGroup(
            @Valid @NotBlank @PathVariable("groupName") String groupName,
            @Valid @NotBlank @PathVariable("adminLogin") String adminLogin
    ) throws NotAnAdminException {
        mGroupManagementsService.deleteGroup(groupName, adminLogin);
    }

    @PutMapping("/api/v1/group/change/admin/{groupName}/{userAdminCandidateLogin}")
    public void changeGroupAdmin(
            @Valid @NotBlank @PathVariable("groupName") String groupName,
            @Valid @NotBlank @PathVariable("userAdminCandidateLogin") String userAdminCandidateLogin
    ) throws InvalidUserExceptions, NotAnAdminException {
        mGroupManagementsService.changeGroupAdmin(groupName, userAdminCandidateLogin);
    }
}
