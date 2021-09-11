package com.chatter.chatter.app.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_groups")
public class GroupEntity {

    @Id
    @GeneratedValue
    private Long groupId;

    private String groupName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "admin_id")
    private UserEntity adminId;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "users_in_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<UserEntity> usersInGroup;

    public GroupEntity(Long groupId, String groupName, UserEntity adminId, Set<UserEntity> usersInGroup) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;
        this.usersInGroup = usersInGroup;
    }

    protected GroupEntity() {}

    public Long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public UserEntity getAdminId() {
        return adminId;
    }

    public void setAdminId(UserEntity adminId) {
        this.adminId = adminId;
    }

    public Set<UserEntity> getUsersInGroup() {
        return usersInGroup;
    }

    public void setUsersInGroup(Set<UserEntity> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(groupName, that.groupName) && Objects.equals(adminId, that.adminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, adminId);
    }
}
