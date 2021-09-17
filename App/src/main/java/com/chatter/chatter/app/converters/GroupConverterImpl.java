package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.web.models.response.GroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupConverterImpl implements GroupConverter {

    @Autowired
    private UserConverter mUserConverter;

    @Override
    public GroupModel convertFromEntityToModel(GroupEntity groupEntity) {
        return new GroupModel(
                groupEntity.getGroupId(), groupEntity.getGroupName(),
                mUserConverter.UserEntityToUserProfile(groupEntity.getAdminId()),
                groupEntity.getUsersInGroup().size()
        );
    }

    @Override
    public List<GroupModel> convertFromEntityListToModelList(List<GroupEntity> groupEntityList) {
        return groupEntityList.stream().map(this::convertFromEntityToModel).collect(Collectors.toList());
    }
}
