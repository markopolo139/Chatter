package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.request.GroupMessagePayload;
import com.chatter.chatter.web.models.response.GroupMessageModel;
import com.chatter.core.values.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GroupMessageConverterImpl implements GroupMessageConverter{

    @Autowired
    private GroupRepository mGroupRepository;

    @Autowired
    private UserRepository mUserRepository;

    @Override
    public GroupMessagesEntity payloadToEntity(GroupMessagePayload groupMessagePayload) {
        return new GroupMessagesEntity(
                null,
                mUserRepository.findByLogin(groupMessagePayload.userFromLogin)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")),
                mGroupRepository.findByGroupNameAAndAdminId_Login(
                        groupMessagePayload.groupToName, groupMessagePayload.groupToAdminLogin
                ).orElseThrow(() -> new UsernameNotFoundException("User not found")),
                groupMessagePayload.content,
                MessageStatus.SEND
        );
    }

    @Override
    public GroupMessageModel entityToModel(GroupMessagesEntity groupMessagesEntity) {
        return new GroupMessageModel(
                groupMessagesEntity.getUserFromId().getLogin(),
                groupMessagesEntity.getGroupToId().getAdminId().getLogin(),
                groupMessagesEntity.getGroupToId().getGroupName(),
                groupMessagesEntity.getContent(),
                groupMessagesEntity.getStatus().name(),
                groupMessagesEntity.getWhenSend()
        );
    }

    @Override
    public NotificationModel modelToNotification(GroupMessageModel groupMessageModel) {
        return new NotificationModel(groupMessageModel.userFromLogin, groupMessageModel.groupToName);
    }
}
