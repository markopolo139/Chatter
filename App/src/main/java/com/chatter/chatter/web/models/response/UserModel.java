package com.chatter.chatter.web.models.response;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModel {

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @NotBlank
    public String login;

    @NotNull
    @Min(0)
    public int howManyFriends;

    @Nullable
    public String profilePhotoPath;

    public UserModel(
            String firstName, String lastName, String login, int howManyFriends, @Nullable String profilePhotoPath
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.howManyFriends = howManyFriends;
        this.profilePhotoPath = profilePhotoPath;
    }

}
