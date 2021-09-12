package com.chatter.chatter.app.services;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.InvalidContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ProfilePhotoService {

    @Autowired
    private UserRepository mUserRepository;

    public Resource getProfilePhoto(String login) {

        return new ByteArrayResource(
                mUserRepository.findByLogin(login)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                        .getUserEntityDetails().getPhoto()
        );

    }

    public void setProfilePhoto(String login, MultipartFile multipartFile) throws IOException, InvalidContentTypeException {

        if (!Objects.equals(multipartFile.getContentType(), MediaType.IMAGE_JPEG_VALUE)
                && !Objects.equals(multipartFile.getContentType(), MediaType.IMAGE_PNG_VALUE))
            throw new InvalidContentTypeException();

        UserEntity userEntity = mUserRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        userEntity.getUserEntityDetails().setPhoto(multipartFile.getBytes());

        mUserRepository.save(userEntity);
    }

}
