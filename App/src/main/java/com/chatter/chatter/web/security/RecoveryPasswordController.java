package com.chatter.chatter.web.security;

import com.chatter.chatter.app.exceptions.InvalidPasswordException;
import com.chatter.chatter.app.services.ForgotPasswordService;
import com.chatter.chatter.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin
@Validated
public class RecoveryPasswordController {

    @Autowired
    private ForgotPasswordService mForgotPasswordService;

    @PostMapping("/forgot/password")
    public void sendEmail(
            @Valid @Email @RequestParam(name = "email") String email, HttpServletRequest httpServletRequest
    ) throws MessagingException, UnsupportedEncodingException {
        mForgotPasswordService.createTokenAndSendEmail(email, WebUtils.getBaseUrl(httpServletRequest));
    }

    @GetMapping("/password/recovery/token")
    public String getToken(@Valid @NotBlank @RequestParam(name = "token") String token) {
        return token;
    }

    @PutMapping("/password/recovery")
    public void updatePassword(
            @Valid @NotBlank @RequestParam(name = "token") String token,
            @Valid @NotBlank @RequestParam(name = "newPassword") String newPassword
    ) throws InvalidPasswordException {
        mForgotPasswordService.updatePassword(token,newPassword);
    }

}
