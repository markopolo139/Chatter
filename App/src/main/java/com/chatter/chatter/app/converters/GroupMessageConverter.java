package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.response.GroupMessageModel;

import java.util.List;

public interface GroupMessageConverter {

    GroupMessagesEntity payloadToEntity(GroupMessageModel groupMessageModel);

    GroupMessageModel entityToModel(GroupMessagesEntity groupMessagesEntity);

    NotificationModel modelToNotification(GroupMessageModel groupMessageModel);

    List<GroupMessageModel> entityListToModel(List<GroupMessagesEntity> groupMessagesEntities);

}
