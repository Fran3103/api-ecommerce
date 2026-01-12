package com.ecommerce.users.controller;

import com.ecommerce.users.dto.UserDto;
import com.ecommerce.users.entity.User;
import com.ecommerce.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/{id}")
    public Optional<UserDto> getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }


    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }


    @PatchMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.removeUser(id);
    }

}
