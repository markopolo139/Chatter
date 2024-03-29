package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.web.models.response.UserModel;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter{
    @Override
    public UserModel UserEntityToUserProfile(UserEntity userEntity) {
        FileSystemResourceLoader loader = new FileSystemResourceLoader();
        String profilePhotoPath = userEntity.getUserEntityDetails().getPhoto();
        return new UserModel(
                userEntity.getUserEntityDetails().getFirstName(),
                userEntity.getUserEntityDetails().getLastName(),
                userEntity.getLogin(),
                userEntity.getFriends().size(),
                profilePhotoPath
                );
    }

    @Override
    public List<UserModel> UserEntityListToModelList(Set<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::UserEntityToUserProfile).collect(Collectors.toList());
    }

    @Override
    public List<UserModel> FriendsToUserModelList(UserEntity userEntity) {
        return userEntity.getFriends().stream().map(this::UserEntityToUserProfile).collect(Collectors.toList());
    }

    @Override
    public List<UserModel> RequestsToUserModelList(UserEntity userEntity) {
        return userEntity.getPendingRequest().stream().map(this::UserEntityToUserProfile).collect(Collectors.toList());
    }

}
