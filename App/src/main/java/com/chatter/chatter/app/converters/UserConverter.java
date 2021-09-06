package com.chatter.chatter.app.converters;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.web.models.response.UserModel;

import java.util.List;

public interface UserConverter {

    UserModel UserEntityToUserProfile(UserEntity userEntity);

    List<UserModel> FriendsToUserModelList(UserEntity userEntity);

    List<UserModel> RequestsToUserModelList(UserEntity userEntity);
}
