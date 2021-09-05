package com.chatter.chatter.app.services;

import com.chatter.chatter.app.data.embedabble.UserEntityDetails;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.InvalidPasswordException;
import com.chatter.chatter.app.exceptions.UserAlreadyExistsException;
import com.chatter.chatter.web.models.request.RegisterPayload;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class RegisterService {

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private PasswordEncoder mPasswordEncoder;

    public void saveUser(RegisterPayload registerPayload) throws UserAlreadyExistsException, InvalidPasswordException {

        if (mUserRepository.findByLogin(registerPayload.login).isPresent()
                || mUserRepository.findByEmail(registerPayload.email).isPresent())
            throw new UserAlreadyExistsException();

        validatePassword(registerPayload.password);

        mUserRepository.save(
                new UserEntity(
                        null, registerPayload.login, mPasswordEncoder.encode(registerPayload.password),
                        registerPayload.email,null,true,
                        Collections.emptySet(), Collections.emptySet(),
                        new UserEntityDetails(registerPayload.firstName,registerPayload.lastName,null),
                        Collections.singleton("USER")
                )
        );
    }

    private void validatePassword(String password) throws InvalidPasswordException {
        PasswordValidator passwordValidator = new PasswordValidator(
                Arrays.asList(
                        new LengthRule(5,30),
                        new UppercaseCharacterRule(1),
                        new WhitespaceRule(),
                        new SpecialCharacterRule(1),
                        new DigitCharacterRule(1)
                )
        );

        RuleResult ruleResult = passwordValidator.validate(new PasswordData(password));

        if (!ruleResult.isValid())
            throw new InvalidPasswordException(ruleResult.getDetails().toString());
    }

}
