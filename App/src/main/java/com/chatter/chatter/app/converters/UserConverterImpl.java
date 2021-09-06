package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.web.models.response.UserModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter{
    @Override
    public UserModel UserEntityToUserProfile(UserEntity userEntity) {
        return new UserModel(
                userEntity.getUserEntityDetails().getFirstName(),
                userEntity.getUserEntityDetails().getLastName(),
                userEntity.getLogin(),
                userEntity.getFriends().size(),
                userEntity.getUserEntityDetails().getPhoto() != null ?
                        userEntity.getUserEntityDetails().getPhoto() : null
                );
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
