package com.chatter.chatter.app.services;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.InvalidPasswordException;
import com.chatter.chatter.app.utils.AppUtils;
import com.chatter.chatter.web.utils.WebUtils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Service
public class ForgotPasswordService {

    public static final String FROM_EMAIL = "mareks.seget@onet.pl";

    public static final String RECOVERY_PATH = "/password/recovery/token?token=";

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private JavaMailSender mJavaMailSender;

    @Autowired
    private PasswordEncoder mPasswordEncoder;

    public void createTokenAndSendEmail(String email, String baseUrl) throws MessagingException, UnsupportedEncodingException {

        UserEntity userEntity =
                mUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = RandomString.make(30);

        String requestUrl = baseUrl + RECOVERY_PATH + token;

        userEntity.setPasswordToken(token);
        mUserRepository.save(userEntity);

        sendEmail(email,requestUrl);
    }

    private void sendEmail(String email, String requestUrl) throws MessagingException, UnsupportedEncodingException {

        MimeMessage mimeMessage = mJavaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom(FROM_EMAIL, "Administration");
        mimeMessageHelper.setTo(email);

        mimeMessageHelper.setSubject("Recover Password");

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + requestUrl + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        mimeMessageHelper.setText(content,true);

        mJavaMailSender.send(mimeMessage);
    }

    public void updatePassword(String token, String newPassword) throws InvalidPasswordException {

        UserEntity userEntity =
                mUserRepository.findByPasswordToken(token).orElseThrow(() -> new UsernameNotFoundException("Invalid token"));

        AppUtils.validatePassword(newPassword);

        userEntity.setPassword(mPasswordEncoder.encode(newPassword));
        userEntity.setPasswordToken(null);

        mUserRepository.save(userEntity);

    }

}
