package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByGroupNameAAndAdminId_Login(String groupName, String adminLogin);

    List<GroupEntity> findAllByGroupNameLike(String groupNamePattern);

}
