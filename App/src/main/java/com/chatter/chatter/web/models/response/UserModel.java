package com.chatter.chatter.web.models.response;

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

    public UserModel(
            String firstName, String lastName, String login, int howManyFriends
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.howManyFriends = howManyFriends;
    }

}
