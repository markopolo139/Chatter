package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByGroupNameAndAdminId_Login(String groupName, String adminLogin);

    List<GroupEntity> findAllByGroupNameLike(String groupNamePattern);

    @Query(value = "select ug.group_id from user_groups ug" +
            " where ug.admin_id = (select user_id from app_users where app_users.login = :login) " +
            "or ug.group_id in (select uig.group_id from app_users au join users_in_group uig on au.user_id = uig.friend_id where login = :login)", nativeQuery = true)
    List<Long> findAllGroupsWithLogin(@Param("login") String login);

}
