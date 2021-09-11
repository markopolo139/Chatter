package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.app.data.entity.UserMessagesEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.request.UserMessagePayload;
import com.chatter.chatter.web.models.response.GroupMessageModel;
import com.chatter.chatter.web.models.response.UserMessageModel;
import com.chatter.core.values.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserMessageConverterImpl implements UserMessageConverter{

    @Autowired
    private UserRepository mUserRepository;

    @Override
    public UserMessagesEntity payloadToEntity(UserMessagePayload userMessagePayload) {
        return new UserMessagesEntity(
                null,
                mUserRepository.findByLogin(userMessagePayload.userFromLogin)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")),
                mUserRepository.findByLogin(userMessagePayload.userToLogin)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")),
                userMessagePayload.content,
                MessageStatus.SEND
        );
    }

    @Override
    public UserMessageModel entityToModel(UserMessagesEntity userMessagesEntity) {
        return new UserMessageModel(
                userMessagesEntity.getUserFromId().getLogin(),
                userMessagesEntity.getUserToId().getLogin(),
                userMessagesEntity.getContent(),
                userMessagesEntity.getStatus().name(),
                userMessagesEntity.getWhenSend()
        );
    }

    @Override
    public NotificationModel modelToNotification(UserMessageModel userMessageModel) {
        return new NotificationModel(userMessageModel.userFromLogin,null);
    }
}
