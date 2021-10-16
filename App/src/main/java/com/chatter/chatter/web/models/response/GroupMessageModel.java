package com.chatter.chatter.web.models.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GroupMessageModel {

    public Long messageId;

    @NotBlank
    public String userFromLogin;

    @NotBlank
    public String groupToAdminLogin;

    @NotBlank
    public String groupToName;

    @NotBlank
    public String content;

    @NotBlank
    public String status;

    @NotNull
    public LocalDateTime whenSend;

    public GroupMessageModel(
            String userFromLogin, String groupToAdminLogin, String groupToName,
            String content, String status, LocalDateTime whenSend, Long messageId
    ) {
        this.userFromLogin = userFromLogin;
        this.groupToAdminLogin = groupToAdminLogin;
        this.groupToName = groupToName;
        this.content = content;
        this.status = status;
        this.whenSend = whenSend;
        this.messageId = messageId;
    }
}
