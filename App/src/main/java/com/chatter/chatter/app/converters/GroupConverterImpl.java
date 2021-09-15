package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.web.models.response.GroupModel;
import org.springframework.stereotype.Component;

@Component
public class GroupConverterImpl implements GroupConverter {
    @Override
    public GroupModel convertFromEntityToModel(GroupEntity groupEntity) {
        return new GroupModel(
                groupEntity.getGroupId(), groupEntity.getGroupName(),
                groupEntity.getAdminId().getLogin(), groupEntity.getUsersInGroup().size()
        );
    }
}
