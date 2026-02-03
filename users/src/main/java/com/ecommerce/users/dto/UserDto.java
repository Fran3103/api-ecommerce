package com.ecommerce.users.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDto {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;
    @Schema(description = "Username of the user", example = "johndoe")
    private String username;
    @Schema(description = "Email address of the user", example = "johndoe@example.com")
    private String email;
    @Schema(description = "First name of the user", example = "John")
    private String name;
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;
    @Schema(description = "Role of the user", example = "ADMIN")
    private String role;
}
