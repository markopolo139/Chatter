package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.exceptions.UserRequestException;
import com.chatter.chatter.app.services.UserRequestService;
import com.chatter.chatter.web.errorHandler.validators.RequestOptionValidator;
import com.chatter.chatter.web.models.response.UserModel;
import com.chatter.core.values.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@CrossOrigin
@Validated
public class UserRequestController {

    @Autowired
    private UserRequestService mUserRequestService;

    @PutMapping("/api/v1/send/request/{login}")
    public void sendRequest(@PathVariable("login") @Valid @NotBlank String userRequestedLogin)
            throws UserRequestException {
        mUserRequestService.sendRequest(userRequestedLogin);
    }

    @PutMapping("/api/v1/handle/request/{login}/{requestOption}")
    public void handleRequest(
            @PathVariable("login") @Valid @NotBlank String userRequestedLogin,
            @PathVariable("requestOption") @Valid @RequestOptionValidator String requestOption
    ) throws UserRequestException {
        mUserRequestService.handleRequest(userRequestedLogin, RequestOptions.valueOf(requestOption));
    }

    @GetMapping("/api/v1/search/friends/{pattern}")
    public List<UserModel> searchFriends(@PathVariable(value = "pattern",required = false) String pattern) {
        if (pattern == null)
            pattern = "";
        return mUserRequestService.findFriends(pattern);
    }

}
