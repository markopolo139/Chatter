package com.chatter.chatter.app.services;

import com.chatter.chatter.app.data.embedabble.UserEntityDetails;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.UserAlreadyExistsException;
import com.chatter.chatter.web.models.request.RegisterPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegisterService {

    @Autowired
    private UserRepository mUserRepository;

    public void saveUser(RegisterPayload registerPayload) throws UserAlreadyExistsException {

        if (mUserRepository.findByLogin(registerPayload.login).isPresent()
                || mUserRepository.findByEmail(registerPayload.email).isPresent())
            throw new UserAlreadyExistsException();

        mUserRepository.save(
                new UserEntity(
                        null, registerPayload.login,registerPayload.password,
                        registerPayload.email,null,true,
                        Collections.emptySet(), Collections.emptySet(),
                        new UserEntityDetails(registerPayload.firstName,registerPayload.lastName,null),
                        Collections.singleton("USER")
                )
        );
    }

}
