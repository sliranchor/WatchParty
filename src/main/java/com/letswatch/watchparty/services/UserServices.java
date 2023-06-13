package com.letswatch.watchparty.services;

import com.letswatch.watchparty.dto.UserDto;
import com.letswatch.watchparty.models.UserEntity;

public interface UserServices {

    void saveUser(UserDto userDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
