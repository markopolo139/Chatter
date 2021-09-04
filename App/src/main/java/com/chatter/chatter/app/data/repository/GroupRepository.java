package com.chatter.chatter.app.data.repository;

import com.chatter.chatter.app.data.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByGroupName(String groupName);

    List<GroupEntity> findAllByGroupNameLike(String groupNamePattern);

}
