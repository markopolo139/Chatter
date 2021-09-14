package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.services.UserProfileService;
import com.chatter.chatter.web.models.response.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class UserProfileController {

    @Autowired
    private UserProfileService mUserProfileService;

    @GetMapping("/api/v1/get/user/profile/{login}")
    public UserModel getUserProfile(@PathVariable(name = "login") @Valid @NotBlank String login) {
        return mUserProfileService.getUserProfile(login);
    }

    @GetMapping("/api/v1/get/user/friends/{login}/{pattern}")
    public List<UserModel> getUserFriends(
            @PathVariable(name = "login") @Valid @NotBlank String login,
            @PathVariable(name = "pattern", required = false) String pattern
    ) {
        if (pattern == null)
            pattern = "";
        return mUserProfileService.getUserFriends(login, pattern);
    }

}
