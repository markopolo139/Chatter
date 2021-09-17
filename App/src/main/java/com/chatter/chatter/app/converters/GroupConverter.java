package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.web.models.response.GroupModel;

import java.util.List;

public interface GroupConverter {

    GroupModel convertFromEntityToModel(GroupEntity groupEntity);

    List<GroupModel> convertFromEntityListToModelList(List<GroupEntity> groupEntityList);
}
