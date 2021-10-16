package com.chatter.chatter.app.data.entity;

import com.chatter.core.values.MessageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_message")
public class UserMessagesEntity {

    @Id
    @GeneratedValue
    private Long userMessageId;

    @OneToOne
    @JoinColumn(name = "user_from_id")
    private UserEntity userFromId;

    @OneToOne
    @JoinColumn(name = "user_to_id")
    private UserEntity userToId;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private LocalDateTime whenSend;

    public UserMessagesEntity(
            Long userMessageId, UserEntity userFromId, UserEntity userToId,
            String content, MessageStatus status, LocalDateTime whenSend
    ) {
        this.userMessageId = userMessageId;
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.content = content;
        this.status = status;
        this.whenSend = whenSend;
    }

    protected UserMessagesEntity() {}

    public Long getUserMessageId() {
        return userMessageId;
    }

    public UserEntity getUserFromId() {
        return userFromId;
    }

    public UserEntity getUserToId() {
        return userToId;
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
        UserMessagesEntity that = (UserMessagesEntity) o;
        return Objects.equals(userMessageId, that.userMessageId)
                && Objects.equals(userFromId, that.userFromId)
                && Objects.equals(userToId, that.userToId)
                && Objects.equals(whenSend, that.whenSend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMessageId, userFromId, userToId, whenSend);
    }
}
