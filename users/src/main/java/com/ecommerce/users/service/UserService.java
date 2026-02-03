package com.ecommerce.users.service;

import com.ecommerce.users.dto.UserDto;
import com.ecommerce.users.entity.User;
import com.ecommerce.users.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepo;



    @Override
    public User createUser(User user) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        User existingUsername = userRepo.findByUsername(user.getUsername());
        if (existingUsername != null) {
            throw new IllegalArgumentException("Username already in use");
        }
        return userRepo.save(user);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userRepo.findById(id).map(user -> {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setLastName(user.getLastName());
            userDto.setRole(user.getRole());
            userDto.setId(user.getId());
            return userDto;
        });
    }

    @Override
    public void removeUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, User userDetails) {

        User user = userRepo.findById(id)
                .orElseThrow(()-> new IllegalStateException("User with id " + id + " does not exist"));


        user.setUsername(userDetails.getUsername() == null ? user.getUsername() : userDetails.getUsername());
        user.setEmail(userDetails.getEmail() == null ? user.getEmail() : userDetails.getEmail());
        user.setName(userDetails.getName() == null ? user.getName() : userDetails.getName());
        user.setLastName(userDetails.getLastName() == null ? user.getLastName() : userDetails.getLastName());
        user.setRole(userDetails.getRole() == null ? user.getRole() : userDetails.getRole());
        return Optional.of(userRepo.save(user));

    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }
}
