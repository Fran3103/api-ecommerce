package com.ecommerce.users.controller;

import com.ecommerce.users.dto.ApiError;
import com.ecommerce.users.dto.UserDto;
import com.ecommerce.users.entity.User;
import com.ecommerce.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name= "User Controller", description = "APIs for managing users")
@ApiResponses(
        {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ApiError.class))),
                @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ApiError.class)))
        }
)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Get user by ID", description = "Retrieves user details by their unique ID")
    @GetMapping("/{id}")
    public Optional<UserDto> getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @Operation(summary = "Create new user", description = "Creates a new user with the provided details")
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @Operation(summary = "Update user by ID", description = "Updates user details by their unique ID")
    @PatchMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @Operation(summary = "Delete user by ID", description = "Deletes a user by their unique ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.removeUser(id);
    }

    @Operation(summary = "Get all users", description = "Retrieves a list of all users")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return service.findAllUsers();
    }

}
