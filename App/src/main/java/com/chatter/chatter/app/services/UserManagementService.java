package com.chatter.chatter.app.services;

import com.chatter.chatter.app.data.embedabble.UserEntityDetails;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.FolderCreationError;
import com.chatter.chatter.app.exceptions.InvalidPasswordException;
import com.chatter.chatter.app.exceptions.UserAlreadyExistsException;
import com.chatter.chatter.app.security.CustomUser;
import com.chatter.chatter.app.utils.AppUtils;
import com.chatter.chatter.web.models.request.RegisterPayload;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Service
public class UserManagementService {

    public static final String RESOURCE_PATH = "src/main/resources/userFolders";

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private GroupRepository mGroupRepository;

    @Autowired
    private PasswordEncoder mPasswordEncoder;

    public void saveUser(RegisterPayload registerPayload) throws Exception {

        if (mUserRepository.findByLogin(registerPayload.login).isPresent()
                || mUserRepository.findByEmail(registerPayload.email).isPresent())
            throw new UserAlreadyExistsException();

        AppUtils.validatePassword(registerPayload.password);

        File file = new File(RESOURCE_PATH + "/" + registerPayload.login);

        if (!file.mkdir())
            throw new FolderCreationError();

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

    public void deleteUser() throws IOException {

        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = customUser.getUsername();

        File file = new File(RESOURCE_PATH + "/" + login);
        FileUtils.deleteDirectory(file);

        mUserRepository.deleteUserFromRequests(login);
        mUserRepository.deleteUserFromFriends(login);
        mUserRepository.deleteUserFromGroup(login);
        mGroupRepository.deleteByAdminId_Login(login);
        mUserRepository.deleteByLogin(login);
    }

}
