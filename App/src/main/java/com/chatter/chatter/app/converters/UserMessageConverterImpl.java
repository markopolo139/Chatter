package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.UserMessagesEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.response.UserMessageModel;
import com.chatter.core.values.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMessageConverterImpl implements UserMessageConverter{

    @Autowired
    private UserRepository mUserRepository;

    @Override
    public UserMessagesEntity modelToEntity(UserMessageModel userMessageModel) {
        return new UserMessagesEntity(
                userMessageModel.messageId,
                mUserRepository.findByLogin(userMessageModel.userFromLogin)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")),
                mUserRepository.findByLogin(userMessageModel.userToLogin)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")),
                userMessageModel.content,
                MessageStatus.valueOf(userMessageModel.status),
                userMessageModel.whenSend == null ? LocalDateTime.now() : userMessageModel.whenSend
        );
    }

    @Override
    public UserMessageModel entityToModel(UserMessagesEntity userMessagesEntity) {
        return new UserMessageModel(
                userMessagesEntity.getUserFromId().getLogin(),
                userMessagesEntity.getUserToId().getLogin(),
                userMessagesEntity.getContent(),
                userMessagesEntity.getStatus().name(),
                userMessagesEntity.getWhenSend(),
                userMessagesEntity.getUserMessageId()
        );
    }

    @Override
    public NotificationModel modelToNotification(UserMessageModel userMessageModel) {
        return new NotificationModel(userMessageModel.userFromLogin,null, userMessageModel.whenSend);
    }

    @Override
    public List<UserMessageModel> entityListToModel(List<UserMessagesEntity> userMessagesEntities) {
        return userMessagesEntities.stream().map(this::entityToModel).collect(Collectors.toList());
    }
}
