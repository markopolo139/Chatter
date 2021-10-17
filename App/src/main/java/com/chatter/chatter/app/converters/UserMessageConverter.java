package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.app.data.entity.UserMessagesEntity;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.response.GroupMessageModel;
import com.chatter.chatter.web.models.response.UserMessageModel;

import java.util.List;

public interface UserMessageConverter {

    UserMessagesEntity modelToEntity(UserMessageModel userMessageModel);

    UserMessageModel entityToModel(UserMessagesEntity userMessagesEntity);

    NotificationModel modelToNotification(UserMessageModel userMessageModel);

    List<UserMessageModel> entityListToModel(List<UserMessagesEntity> userMessagesEntities);


}
