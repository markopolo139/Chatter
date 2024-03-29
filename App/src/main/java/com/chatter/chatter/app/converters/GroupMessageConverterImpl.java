package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.web.models.NotificationModel;
import com.chatter.chatter.web.models.response.GroupMessageModel;
import com.chatter.core.values.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMessageConverterImpl implements GroupMessageConverter{

    @Autowired
    private GroupRepository mGroupRepository;

    @Autowired
    private UserRepository mUserRepository;

    @Override
    public GroupMessagesEntity modelToEntity(GroupMessageModel groupMessageModel) {
        return new GroupMessagesEntity(
                groupMessageModel.messageId,
                mUserRepository.findByLogin(groupMessageModel.userFromLogin)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")),
                mGroupRepository.findByGroupNameAndAdminId_Login(
                        groupMessageModel.groupToName, groupMessageModel.groupToAdminLogin
                ).orElseThrow(() -> new UsernameNotFoundException("User not found")),
                groupMessageModel.content,
                MessageStatus.valueOf(groupMessageModel.status),
                groupMessageModel.whenSend == null ? LocalDateTime.now() : groupMessageModel.whenSend
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
                groupMessagesEntity.getWhenSend(),
                groupMessagesEntity.getGroupMessageId()
        );
    }

    @Override
    public NotificationModel modelToNotification(GroupMessageModel groupMessageModel) {
        return new NotificationModel(
                groupMessageModel.userFromLogin, groupMessageModel.groupToName, groupMessageModel.whenSend
        );
    }

    @Override
    public List<GroupMessageModel> entityListToModel(List<GroupMessagesEntity> groupMessagesEntities) {
        return groupMessagesEntities.stream().map(this::entityToModel).collect(Collectors.toList());
    }
}
