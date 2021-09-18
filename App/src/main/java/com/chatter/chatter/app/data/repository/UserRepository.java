package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByPasswordToken(String passwordToken);

    Optional<UserEntity> findByUserEntityDetails_FirstName(String firstName);

    Optional<UserEntity> findByUserEntityDetails_LastName(String lastName);

    List<UserEntity> findAllByLoginLike(String loginPattern); //TODO: remember to add % at end

    List<UserEntity> findAllByUserEntityDetails_FirstNameLike(String firstNamePattern);

    @Transactional
    @Modifying
    void deleteByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "delete from users_in_group where friend_id = (" +
            "select user_id from app_users where login = :login)", nativeQuery = true)
    void deleteUserFromGroup(@Param("login") String login);

    @Transactional
    @Modifying
    @Query(value = "delete from user_friends where friend_id = (" +
            "select user_id from app_users where login = :login)", nativeQuery = true)
    void deleteUserFromFriends(@Param("login") String login);

    @Transactional
    @Modifying
    @Query(value = "delete from user_friends_request where friend_id = (" +
            "select user_id from app_users where login = :login)", nativeQuery = true)
    void deleteUserFromRequests(@Param("login") String login);
}
