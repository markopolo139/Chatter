package com.chatter.chatter.app.services;

import com.chatter.chatter.app.converters.GroupConverter;
import com.chatter.chatter.app.converters.UserConverter;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.web.models.response.GroupModel;
import com.chatter.chatter.web.models.response.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class GroupProfileService {

    @Autowired
    private GroupConverter mGroupConverter;

    @Autowired
    private GroupRepository mGroupRepository;

    @Autowired
    private UserConverter mUserConverter;

    private Pattern mPattern;
    private Matcher mMatcher;

    public List<GroupModel> getGroupsWhereIsSelectedUser(String login, String pattern) {

        if(pattern.equals(""))
            mPattern = Pattern.compile("\\w*");
        else
            mPattern = Pattern.compile(pattern + ".*");

        List<GroupModel> groupsOfUser =
                mGroupConverter.convertFromEntityListToModelList(mGroupRepository.findAllGroupsWithLogin(login));

        return groupsOfUser.stream().filter(i ->
                {
                    mMatcher = mPattern.matcher(i.groupName);
                    return mMatcher.matches();
                }
        ).collect(Collectors.toList());

    }

    public List<UserModel> getUsersInGroup(Long groupId) {
        return mUserConverter.UserEntityListToModelList(
                mGroupRepository.getById(groupId).getUsersInGroup()
        );
    }

}
