package com.chatter.chatter.app.services;

import com.chatter.chatter.app.converters.UserConverter;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.web.models.response.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private UserConverter mUserConverter;

    private Pattern mPattern;
    private Matcher mMatcher;

    public UserModel getUserProfile(String login) {
        return mUserConverter.UserEntityToUserProfile(
                mUserRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );
    }

    public List<UserModel> getUserFriends(String login, String pattern) {
        if(pattern.equals(""))
            mPattern = Pattern.compile("\\w*");
        else
            mPattern = Pattern.compile(pattern + ".*");

        List<UserModel> userFriends = mUserConverter.FriendsToUserModelList(
                mUserRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );

        return userFriends.stream().filter( i -> {
            mMatcher = mPattern.matcher(i.login);
            return mMatcher.matches();
        }).collect(Collectors.toList());
    }

}
