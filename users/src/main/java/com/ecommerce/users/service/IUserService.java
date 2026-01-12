package com.ecommerce.users.service;

import com.ecommerce.users.dto.UserDto;
import com.ecommerce.users.entity.User;

import java.util.Optional;

public interface IUserService {

    User createUser(User user);

    Optional<UserDto> getUserById(Long  id);

    void removeUser(Long id);

    Optional<User> updateUser(Long id, User userDetails);


}
