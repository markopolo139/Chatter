package com.chatter.chatter.web;

import com.chatter.chatter.app.data.embedabble.UserEntityDetails;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private UserRepository mUserRepository;

    @GetMapping("api/v1/test")
    public UserEntity testUser(){
        mUserRepository.save(new UserEntity(null,"marek","zsk", "test", null, true, null, null, new UserEntityDetails("Marek", "Seget", null)));

        return mUserRepository.getById(1L);
    }

}
