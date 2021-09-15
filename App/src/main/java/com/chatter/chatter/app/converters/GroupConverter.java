package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.web.models.response.GroupModel;

public interface GroupConverter {

    GroupModel convertFromEntityToModel(GroupEntity groupEntity);
}
