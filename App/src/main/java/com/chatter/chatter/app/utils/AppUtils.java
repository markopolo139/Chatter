package com.chatter.chatter.app.utils;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.InvalidPasswordException;
import org.passay.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

public class AppUtils {

    public static UserEntity getUserFromLogin(String login, UserRepository userRepository) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public static void validatePassword(String password) throws InvalidPasswordException {
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
