package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByGroupNameAndAdminId_Login(String groupName, String adminLogin);

    List<GroupEntity> findAllByGroupNameLike(String groupNamePattern);

    @Query(value = "select * from user_groups ug" +
            " where ug.admin_id = (select user_id from app_users where app_users.login = :login) " +
            "or ug.group_id in (select uig.group_id from app_users au join users_in_group uig " +
            "on au.user_id = uig.friend_id where login = :login)", nativeQuery = true)
    List<GroupEntity> findAllGroupsWithLogin(@Param("login") String login);

    @Transactional
    @Modifying
    void deleteByAdminId_Login(String login);

    @Transactional
    @Modifying
    void deleteByAdminId_LoginAndGroupName(String login, String groupName);

}
