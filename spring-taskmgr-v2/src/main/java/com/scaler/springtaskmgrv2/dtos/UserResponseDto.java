package com.scaler.springtaskmgrv2.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {
    Integer id;
    String email;
    String username;
    String bio;
    String image;
    String token;
}
