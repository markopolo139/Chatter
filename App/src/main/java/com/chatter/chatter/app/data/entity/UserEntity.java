package com.chatter.chatter.app.data.entity;

import com.chatter.chatter.app.data.embedabble.UserEntityDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long userId;

    private String login;

    private String password;

    private String email;

    @Nullable
    private String passwordToken;

    private boolean enabled;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="user_friends",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="friend_id")
    )
    private Set<UserEntity> friends;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_friends_request", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<UserEntity> pendingRequest;

    @Embedded
    private UserEntityDetails userEntityDetails;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> userRoles;

    public UserEntity(
            Long userId, String login, String password, String email, String passwordToken, boolean enabled,
            Set<UserEntity> friends, Set<UserEntity> pendingRequest, UserEntityDetails userEntityDetails, Set<String> userRoles
    ) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.passwordToken = passwordToken;
        this.enabled = enabled;
        this.friends = friends;
        this.pendingRequest = pendingRequest;
        this.userEntityDetails = userEntityDetails;
        this.userRoles = userRoles;
    }

    protected UserEntity() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserEntity> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserEntity> friends) {
        this.friends = friends;
    }

    public Set<UserEntity> getPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(Set<UserEntity> pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    public UserEntityDetails getUserEntityDetails() {
        return userEntityDetails;
    }

    public void setUserEntityDetails(UserEntityDetails userEntityDetails) {
        this.userEntityDetails = userEntityDetails;
    }

    public Set<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<String> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId.equals(that.userId) && login.equals(that.login) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password);
    }
}
