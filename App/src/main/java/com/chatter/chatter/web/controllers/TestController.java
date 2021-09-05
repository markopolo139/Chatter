package com.chatter.chatter.web;

import com.chatter.chatter.app.data.embedabble.UserEntityDetails;
import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.app.data.repository.UserRepository;
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

    @GetMapping("api/v1/test")
    public GroupEntity testUser() throws Exception {

        UserEntity userEntity = mUserRepository.findById(1L).orElseThrow(Exception::new);
        UserEntity userEntity2 = mUserRepository.findById(2L).orElseThrow(Exception::new);

        GroupEntity groupEntity = new GroupEntity(null,"test", userEntity,Collections.singleton(userEntity2));

        mGroupRepository.save(groupEntity);

        return mGroupRepository.findById(2L).orElseThrow(Exception::new);
    }

}
