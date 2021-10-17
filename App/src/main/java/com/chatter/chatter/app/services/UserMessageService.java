package com.chatter.chatter.app.services;

import com.chatter.chatter.app.converters.UserMessageConverter;
import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.app.data.entity.UserMessagesEntity;
import com.chatter.chatter.app.data.repository.UserMessageRepository;
import com.chatter.chatter.app.exceptions.GroupDoesNotExists;
import com.chatter.chatter.app.exceptions.UserRequestException;
import com.chatter.chatter.app.security.CustomUser;
import com.chatter.chatter.web.models.response.GroupMessageModel;
import com.chatter.chatter.web.models.response.UserMessageModel;
import com.chatter.core.values.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageService {

    @Autowired
    private UserMessageRepository mUserMessageRepository;

    @Autowired
    private UserMessageConverter mUserMessageConverter;

    @Autowired
    private SimpMessagingTemplate mSimpMessagingTemplate;

    public List<UserMessageModel> getAllMessages(String chatPersonLogin)
            throws GroupDoesNotExists, UserRequestException {

        String loggedInUser =
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        List<UserMessagesEntity> userMessagesEntities = (chatPersonLogin.equals(loggedInUser)) ?
                mUserMessageRepository.findAllByUserToId_LoginAndUserFromId_Login(loggedInUser, chatPersonLogin)
                : mUserMessageRepository.findAllMessagesBetweenLoggedInAndChatPerson(loggedInUser,chatPersonLogin);

        userMessagesEntities.forEach(i -> {
            if (!(i.getStatus() == MessageStatus.READ))
                i.setStatus(MessageStatus.READ);
        });

        mUserMessageRepository.saveAll(userMessagesEntities);

        return mUserMessageConverter.entityListToModel(userMessagesEntities);

    }

    public void deleteUserMessage(Long messageId) {
        mUserMessageRepository.deleteById(messageId);
    }

    public void processUserMessage(UserMessageModel userMessageModel) {

        UserMessagesEntity userMessagesEntity = mUserMessageConverter.modelToEntity(userMessageModel);
        userMessagesEntity = mUserMessageRepository.save(userMessagesEntity);

        userMessageModel = mUserMessageConverter.entityToModel(userMessagesEntity);

        if (!userMessageModel.userToLogin.equals(userMessageModel.userFromLogin)) {
            mSimpMessagingTemplate.convertAndSendToUser(
                    userMessageModel.userFromLogin, "/queue/messages/" + userMessageModel.userToLogin, userMessageModel
            );

            mSimpMessagingTemplate.convertAndSendToUser(
                    userMessageModel.userToLogin, "/queue/notification",
                    mUserMessageConverter.modelToNotification(userMessageModel)
            );
        }

        mSimpMessagingTemplate.convertAndSendToUser(
                userMessageModel.userToLogin, "/queue/messages/" + userMessageModel.userFromLogin, userMessageModel
        );

    }

    public void saveUserMessage(UserMessageModel userMessageModel) {
        mUserMessageRepository.save(mUserMessageConverter.modelToEntity(userMessageModel));
    }

}
