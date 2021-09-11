package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.UserMessagesEntity;
import com.chatter.core.values.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMessageRepository extends JpaRepository<UserMessagesEntity, Long> {

    List<UserMessagesEntity> findAllByUserToId_LoginAndUserFromId_Login(String userToLogin, String userFromLogin);

    List<UserMessagesEntity> findAllByUserToId_LoginAndUserFromId_LoginAndStatus(
            String userToLogin, String userFromLogin, MessageStatus status);

}
