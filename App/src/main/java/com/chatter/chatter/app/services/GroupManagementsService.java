package com.chatter.chatter.app.services;

import com.chatter.chatter.app.converters.GroupConverter;
import com.chatter.chatter.app.data.entity.GroupEntity;
import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.GroupRepository;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.GroupAlreadyExistException;
import com.chatter.chatter.app.exceptions.GroupDoesNotExists;
import com.chatter.chatter.app.exceptions.InvalidUserExceptions;
import com.chatter.chatter.app.exceptions.NotAnAdminException;
import com.chatter.chatter.app.security.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class GroupManagementsService {

    @Autowired
    private GroupRepository mGroupRepository;

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private GroupConverter mGroupConverter;

    public void createGroup(String groupName) throws GroupAlreadyExistException {

        String adminLogin =
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (mGroupRepository.findByGroupNameAndAdminId_Login(groupName, adminLogin).isPresent())
            throw new GroupAlreadyExistException();

        saveNewGroup(adminLogin,groupName);

    }

    public void addUser(String groupName, String adminLogin, String addUserLogin)
            throws GroupDoesNotExists, InvalidUserExceptions {

        GroupEntity groupEntity = mGroupRepository.findByGroupNameAndAdminId_Login(groupName,adminLogin)
                .orElseThrow(GroupDoesNotExists::new);

        String loggedInUserLogin =
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (groupEntity.getUsersInGroup().stream().map(UserEntity::getLogin).noneMatch(i -> i.equals(loggedInUserLogin)))
            throw new InvalidUserExceptions("You are not in selected group");

        UserEntity addUser = mUserRepository.findByLogin(adminLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (groupEntity.getUsersInGroup().contains(addUser))
            throw new InvalidUserExceptions("User to add is already in group");

        groupEntity.getUsersInGroup().add(addUser);

        mGroupRepository.save(groupEntity);
    }

    public void deleteUser(String groupName, String deleteUserLogin) throws NotAnAdminException, InvalidUserExceptions {

        String adminLogin =
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        GroupEntity groupEntity = mGroupRepository.findByGroupNameAndAdminId_Login(groupName, adminLogin)
                .orElseThrow(NotAnAdminException::new);

        UserEntity userEntity = mUserRepository.findByLogin(deleteUserLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!groupEntity.getUsersInGroup().contains(userEntity))
            throw new InvalidUserExceptions("Selected user is not in selected group");

        groupEntity.getUsersInGroup().remove(userEntity);

        mGroupRepository.save(groupEntity);


    }

    public void leaveGroup(String groupName, String adminLogin) throws GroupDoesNotExists, InvalidUserExceptions {

        String loggedInUserLogin =
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (loggedInUserLogin.equals(adminLogin)) {
            deleteGroup(groupName,adminLogin);
            return;
        }

        GroupEntity groupEntity = mGroupRepository.findByGroupNameAndAdminId_Login(groupName,adminLogin)
                .orElseThrow(GroupDoesNotExists::new);

        UserEntity userEntity = mUserRepository.findByLogin(loggedInUserLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!groupEntity.getUsersInGroup().contains(userEntity))
            throw new InvalidUserExceptions("Logged in user is not in selected group");

        groupEntity.getUsersInGroup().remove(userEntity);

        mGroupRepository.save(groupEntity);

    }

    public void changeGroupAdmin(String groupName, String userAdminCandidateLogin)
            throws NotAnAdminException, InvalidUserExceptions {

        String adminLogin =
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        GroupEntity groupEntity = mGroupRepository.findByGroupNameAndAdminId_Login(groupName, adminLogin)
                .orElseThrow(NotAnAdminException::new);

        if (groupEntity.getUsersInGroup().stream().map(UserEntity::getLogin)
                .noneMatch(i -> i.equals(userAdminCandidateLogin)))
            throw new InvalidUserExceptions("Admin candidate is not in group");

        groupEntity.setAdminId(mUserRepository.findByLogin(userAdminCandidateLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));

        mGroupRepository.save(groupEntity);

    }

    public void deleteGroup(String groupName, String adminLogin) {
        mGroupRepository.deleteByAdminId_LoginAndGroupName(groupName, adminLogin);
    }

    private void saveNewGroup(String adminLogin, String groupName) {
        UserEntity admin = mUserRepository.findByLogin(adminLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        mGroupRepository.save(
                new GroupEntity(null, groupName, admin, new HashSet<>(Collections.singletonList(admin)))
        );
    }

}
