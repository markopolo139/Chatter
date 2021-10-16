package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.UserMessagesEntity;
import com.chatter.core.values.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMessageRepository extends JpaRepository<UserMessagesEntity, Long> {

    List<UserMessagesEntity> findAllByUserToId_LoginAndUserFromId_Login(String userToLogin, String userFromLogin);

    List<UserMessagesEntity> findAllByUserToId_LoginAndUserFromId_LoginAndStatus(
            String userToLogin, String userFromLogin, MessageStatus status
    );

    @Query(
            "select use from UserMessagesEntity use where use.userFromId.login in (:loggedInLogin, :chatPersonLogin)" +
            " and use.userToId.login in(:loggedInLogin, :chatPersonLogin) and use.userFromId.login <> use.userToId.login "
    )
    List<UserMessagesEntity> findAllMessagesBetweenLoggedInAndChatPerson(
            @Param("loggedInLogin") String loggedInLogin, @Param("chatPersonLogin") String chatPersonLogin
    );

}
