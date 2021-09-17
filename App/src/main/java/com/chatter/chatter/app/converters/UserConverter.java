package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.web.models.response.UserModel;

import java.util.List;
import java.util.Set;

public interface UserConverter {

    UserModel UserEntityToUserProfile(UserEntity userEntity);

    List<UserModel> UserEntityListToModelList(Set<UserEntity> userEntityList);

    List<UserModel> FriendsToUserModelList(UserEntity userEntity);

    List<UserModel> RequestsToUserModelList(UserEntity userEntity);
}
