package com.chatter.chatter.app.services;

import com.chatter.chatter.app.data.entity.UserEntity;
import com.chatter.chatter.app.data.repository.UserRepository;
import com.chatter.chatter.app.security.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository mUserRepository;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = mUserRepository.findByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );

        return CustomUser.customBuilder()
                .username(userEntity.getLogin())
                .password(userEntity.getPassword())
                .authorities(userEntity.getUserRoles().toArray(new String[]{}))
                .disabled(!userEntity.isEnabled())
                .firstName(userEntity.getUserEntityDetails().getFirstName())
                .lastName(userEntity.getUserEntityDetails().getLastName())
                .photo(userEntity.getUserEntityDetails().getPhoto())
                .build();
    }
}
