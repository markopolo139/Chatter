package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.converters.UserConverter;
import com.chatter.chatter.app.data.embedabble.UserEntityDetails;
import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.web.models.response.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private GroupRepository mGroupRepository;

    @Autowired
    private UserConverter mUserConverter;

    @GetMapping("api/v1/test")
    public UserModel testUser() throws Exception {

        UserEntity userEntity = mUserRepository.findById(1L).orElseThrow(Exception::new);

        return mUserConverter.UserEntityToUserProfile(userEntity);

    }

}
