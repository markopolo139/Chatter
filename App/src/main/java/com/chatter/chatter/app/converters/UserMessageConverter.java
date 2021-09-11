package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.app.data.entity.UserMessagesEntity;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.request.GroupMessagePayload;
import com.chatter.chatter.web.models.request.UserMessagePayload;
import com.chatter.chatter.web.models.response.GroupMessageModel;
import com.chatter.chatter.web.models.response.UserMessageModel;

public interface UserMessageConverter {

    UserMessagesEntity payloadToEntity(UserMessagePayload userMessagePayload);

    UserMessageModel entityToModel(UserMessagesEntity userMessagesEntity);

    NotificationModel modelToNotification(UserMessageModel userMessageModel);

}
