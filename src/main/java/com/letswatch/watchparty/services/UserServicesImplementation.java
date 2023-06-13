package com.letswatch.watchparty.services;

import com.letswatch.watchparty.dto.UserDto;
import com.letswatch.watchparty.models.Role;
import com.letswatch.watchparty.models.UserEntity;
import com.letswatch.watchparty.repository.RoleRepository;
import com.letswatch.watchparty.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServicesImplementation implements UserServices {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServicesImplementation(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);


    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
