package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.exceptions.InvalidPasswordException;
import com.chatter.chatter.app.exceptions.UserAlreadyExistsException;
import com.chatter.chatter.app.services.UserManagementService;
import com.chatter.chatter.web.models.request.RegisterPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserManagementController {

    @Autowired
    private UserManagementService mUserManagementService;

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegisterPayload registerPayload)
            throws UserAlreadyExistsException, InvalidPasswordException {
        mUserManagementService.saveUser(registerPayload);
    }

}
