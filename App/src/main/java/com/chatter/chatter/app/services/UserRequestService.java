package com.chatter.chatter.app.services;

import com.chatter.chatter.app.converters.UserConverter;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.UserRequestException;
import com.chatter.chatter.app.security.CustomUser;
import com.chatter.chatter.web.models.response.UserModel;
import com.chatter.core.values.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserRequestService {

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private UserConverter mUserConverter;

    private Pattern mPattern;
    private Matcher mMatcher;

    public void sendRequest(String userRequestedLogin) throws UserRequestException {

        String login = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        UserEntity loggedInUser = mUserRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserEntity userRequested = mUserRepository.findByLogin(userRequestedLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (loggedInUser.getFriends().contains(userRequested))
            throw new UserRequestException("Selected User is already in friends list");

        userRequested.getPendingRequest().add(loggedInUser);

        mUserRepository.save(userRequested);
    }

    public void handleRequest(String userRequestedLogin, RequestOptions requestOptions) throws UserRequestException {

        String login = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        UserEntity loggedInUser = mUserRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserEntity userRequested = mUserRepository.findByLogin(userRequestedLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!loggedInUser.getPendingRequest().contains(userRequested))
            throw new UserRequestException("Selected user is not in friend requests");

        if (requestOptions.equals(RequestOptions.ACCEPT)) {
            loggedInUser.getFriends().add(userRequested);
            userRequested.getFriends().add(loggedInUser);
        }

        loggedInUser.getPendingRequest().remove(userRequested);

        mUserRepository.saveAll(Arrays.asList(loggedInUser, userRequested));
    }

    public List<UserModel> findFriends(String pattern) {
        if(pattern.equals(""))
            mPattern = Pattern.compile("\\w*");
        else
            mPattern = Pattern.compile(pattern + ".*");

        List<UserModel> users = mUserConverter.UserEntityListToModelList(new HashSet<>(mUserRepository.findAll()));

        return users.stream().filter( i -> {
            mMatcher = mPattern.matcher(i.login);
            return mMatcher.matches();
        }).collect(Collectors.toList());
    }

}
