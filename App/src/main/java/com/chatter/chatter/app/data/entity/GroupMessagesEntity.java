package com.chatter.chatter.app.data.entity;

import com.chatter.core.values.MessageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "group_message")
public class GroupMessagesEntity {

    @Id
    @GeneratedValue
    private Long groupMessageId;

    @OneToOne
    @JoinColumn(name = "user_from_id")
    private UserEntity userFromId;

    @OneToOne
    @JoinColumn(name = "group_to_id")
    private GroupEntity groupToId;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private LocalDateTime whenSend;

    public GroupMessagesEntity(
            Long groupMessageId, UserEntity userFromId, GroupEntity groupToId, String content, MessageStatus status
    ) {
        this.groupMessageId = groupMessageId;
        this.userFromId = userFromId;
        this.groupToId = groupToId;
        this.content = content;
        this.status = status;
        this.whenSend = LocalDateTime.now();
    }

    protected GroupMessagesEntity() {}

    public Long getGroupMessageId() {
        return groupMessageId;
    }

    public UserEntity getUserFromId() {
        return userFromId;
    }

    public GroupEntity getGroupToId() {
        return groupToId;
    }

    public String getContent() {
        return content;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public LocalDateTime getWhenSend() {
        return whenSend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMessagesEntity that = (GroupMessagesEntity) o;
        return Objects.equals(groupMessageId, that.groupMessageId)
                && Objects.equals(userFromId, that.userFromId)
                && Objects.equals(groupToId, that.groupToId)
                && Objects.equals(whenSend, that.whenSend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupMessageId, userFromId, groupToId, whenSend);
    }
}
