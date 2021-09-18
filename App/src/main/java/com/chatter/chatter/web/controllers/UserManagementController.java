package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.exceptions.InvalidPasswordException;
import com.chatter.chatter.app.exceptions.UserAlreadyExistsException;
import com.chatter.chatter.app.services.UserManagementService;
import com.chatter.chatter.web.models.request.RegisterPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@RestController
@CrossOrigin
@Validated
public class UserManagementController {

    @Autowired
    private UserManagementService mUserManagementService;

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegisterPayload registerPayload)
            throws Exception {
        mUserManagementService.saveUser(registerPayload);
    }

    @DeleteMapping("/api/v1/delete/user")
    public void deleteUser() throws IOException {
        mUserManagementService.deleteUser();
    }

    @PutMapping("/api/v1/delete/friend/{login}")
    public void deleteUser(@PathVariable("login") String friendLogin) throws IOException {
        mUserManagementService.deleteFriend(friendLogin);
    }

    @PutMapping("/api/v1/user/admin/{login}")
    public void addAdminRole(@PathVariable("login") String userLogin) throws IOException {
        mUserManagementService.addAdminRole(userLogin);
    }

}
