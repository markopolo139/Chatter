package com.chatter.chatter.app.services;

import com.chatter.chatter.app.converters.GroupMessageConverter;
import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.GroupMessageRepository;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.app.exceptions.GroupDoesNotExists;
import com.chatter.chatter.app.exceptions.UserRequestException;
import com.chatter.chatter.app.security.CustomUser;
import com.chatter.chatter.web.models.response.GroupMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMessageService {

    @Autowired
    private GroupMessageRepository mGroupMessageRepository;

    @Autowired
    private GroupMessageConverter mGroupMessageConverter;

    @Autowired
    private GroupRepository mGroupRepository;

    @Autowired
    private SimpMessagingTemplate mSimpMessagingTemplate;

    public List<GroupMessageModel> getAllMessages(String groupName, String adminLogin)
            throws GroupDoesNotExists, UserRequestException {

        String loggedInUser =
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        GroupEntity groupEntity = mGroupRepository.findByGroupNameAndAdminId_Login(groupName,adminLogin)
                .orElseThrow(GroupDoesNotExists::new);

        if (groupEntity.getUsersInGroup().stream().noneMatch(i -> i.getLogin().equals(loggedInUser)))
            throw new UserRequestException("Logged in user is not in group");

        return mGroupMessageConverter.entityListToModel(
                mGroupMessageRepository.findAllByGroupToId_AdminId_LoginAndGroupToId_GroupName(adminLogin,groupName)
        );

    }

    public void deleteGroupMessage(Long messageId) {
        mGroupMessageRepository.deleteById(messageId);
    }

    public void processGroupMessage(GroupMessageModel groupMessageModel) throws GroupDoesNotExists {

        GroupMessagesEntity groupMessagesEntity = mGroupMessageConverter.modelToEntity(groupMessageModel);

        GroupEntity groupEntity = mGroupRepository.findByGroupNameAndAdminId_Login(
                groupMessageModel.groupToName, groupMessageModel.groupToAdminLogin
        ).orElseThrow(GroupDoesNotExists::new);

        groupMessagesEntity = mGroupMessageRepository.save(groupMessagesEntity);

        mSimpMessagingTemplate.convertAndSend(
                "/topic/" + groupMessageModel.groupToName + "/" + groupMessageModel.groupToAdminLogin,
                mGroupMessageConverter.entityToModel(groupMessagesEntity)
        );

        for (UserEntity ue : groupEntity.getUsersInGroup())
            mSimpMessagingTemplate.convertAndSendToUser(
                    ue.getLogin(), "/queue/notification",
                    mGroupMessageConverter.modelToNotification(groupMessageModel)
            );
    }

    public void saveGroupMessage(GroupMessageModel groupMessageModel) {
        mGroupMessageRepository.save(mGroupMessageConverter.modelToEntity(groupMessageModel));
    }
}
