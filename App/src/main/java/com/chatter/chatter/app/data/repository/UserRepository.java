package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByPasswordToken(String passwordToken);

    Optional<UserEntity> findByUserEntityDetails_FirstName(String firstName);

    Optional<UserEntity> findByUserEntityDetails_LastName(String lastName);

    List<UserEntity> findByLoginLike(String loginPattern); //TODO: remember to add % at end

    List<UserEntity> findByUserEntityDetails_FirstNameLike(String firstNamePattern);

}
