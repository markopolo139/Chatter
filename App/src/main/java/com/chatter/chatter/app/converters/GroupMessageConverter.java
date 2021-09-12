package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.request.GroupMessagePayload;
import com.chatter.chatter.web.models.response.GroupMessageModel;

public interface GroupMessageConverter {

    GroupMessagesEntity payloadToEntity(GroupMessagePayload groupMessagePayload);

    GroupMessageModel entityToModel(GroupMessagesEntity groupMessagesEntity);

    NotificationModel modelToNotification(GroupMessageModel groupMessageModel);

}