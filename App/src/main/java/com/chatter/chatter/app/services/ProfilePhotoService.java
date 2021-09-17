package com.chatter.chatter.app.services;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.exceptions.InvalidContentTypeException;
import com.chatter.chatter.app.exceptions.InvalidPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class ProfilePhotoService {

    @Autowired
    private UserRepository mUserRepository;

    public Resource getProfilePhoto(String login) throws InvalidPathException {

        String filePath = mUserRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                .getUserEntityDetails().getPhoto();

        if (filePath == null)
            throw new InvalidPathException();

        return new FileSystemResource(Paths.get(filePath));

    }

    public void setProfilePhoto(String login, MultipartFile multipartFile) throws IOException, InvalidContentTypeException {

        if (!Objects.equals(multipartFile.getContentType(), MediaType.IMAGE_JPEG_VALUE)
                && !Objects.equals(multipartFile.getContentType(), MediaType.IMAGE_PNG_VALUE))
            throw new InvalidContentTypeException(multipartFile);

        UserEntity userEntity = mUserRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        File file = new File(
                Paths.get(
                        UserManagementService.RESOURCE_PATH + "/" + login + "/profilePhoto"
                ).toAbsolutePath().toUri()
        );

        multipartFile.transferTo(file);

        userEntity.getUserEntityDetails().setPhoto(file.getAbsolutePath());

        mUserRepository.save(userEntity);
    }

}
