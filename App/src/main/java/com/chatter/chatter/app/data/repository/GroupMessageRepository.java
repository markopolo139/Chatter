package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.GroupMessagesEntity;
import com.chatter.core.values.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMessageRepository extends JpaRepository<GroupMessagesEntity, Long> {

    List<GroupMessagesEntity> findAllByGroupToId_AdminId_LoginAndGroupToId_GroupName(String login, String groupName);

    List<GroupMessagesEntity> findAllByGroupToId_AdminId_LoginAndGroupToId_GroupNameAndStatus(
            String login, String groupName, MessageStatus status
    );

}
