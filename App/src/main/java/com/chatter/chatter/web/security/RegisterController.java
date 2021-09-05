package com.chatter.chatter.web.security;

import com.chatter.chatter.app.exceptions.UserAlreadyExistsException;
import com.chatter.chatter.app.services.RegisterService;
import com.chatter.chatter.web.models.request.RegisterPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private RegisterService mRegisterService;

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegisterPayload registerPayload) throws UserAlreadyExistsException {
        mRegisterService.saveUser(registerPayload);
    }

}
