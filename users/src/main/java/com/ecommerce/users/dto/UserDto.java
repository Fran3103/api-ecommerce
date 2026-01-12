package com.ecommerce.users.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private String role;
}
