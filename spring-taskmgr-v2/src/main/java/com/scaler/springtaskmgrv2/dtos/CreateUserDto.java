package com.scaler.springtaskmgrv2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    private String username;
    private String password;
    private String email;
}
