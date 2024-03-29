package com.chatter.chatter.web.controllers;

import com.chatter.chatter.app.exceptions.InvalidContentTypeException;
import com.chatter.chatter.app.exceptions.InvalidPathException;
import com.chatter.chatter.app.security.CustomUser;
import com.chatter.chatter.app.services.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin
@Validated
public class ProfilePhotoController {

    @Autowired
    private ProfilePhotoService mProfilePhotoService;

    @GetMapping(value = "/api/v1/get/profile/photo", produces = MediaType.IMAGE_PNG_VALUE)
    public Resource getProfilePhoto(@Valid @NotBlank @RequestParam String photoPath) throws  InvalidPathException {
        return mProfilePhotoService.getProfilePhotoByPhotoPath(photoPath);
    }

    @PutMapping(value = "/api/v1/set/profile/photo")
    public void setProfilePhoto(@Valid @NotNull @RequestPart("photo") MultipartFile multipartFile)
            throws IOException, InvalidContentTypeException {
        mProfilePhotoService.setProfilePhoto(
                ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(),
                multipartFile
        );
    }

}
