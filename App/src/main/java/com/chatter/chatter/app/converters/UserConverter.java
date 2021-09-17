package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.web.models.response.UserModel;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserConverter {

    UserModel UserEntityToUserProfile(UserEntity userEntity) throws IOException;

    List<UserModel> UserEntityListToModelList(Set<UserEntity> userEntityList) throws IOException;

    List<UserModel> FriendsToUserModelList(UserEntity userEntity) throws IOException;

    List<UserModel> RequestsToUserModelList(UserEntity userEntity) throws IOException;
}
