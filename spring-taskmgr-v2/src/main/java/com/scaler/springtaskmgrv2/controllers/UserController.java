package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.dtos.CreateUserDto;
import com.scaler.springtaskmgrv2.dtos.LoginUserDto;
import com.scaler.springtaskmgrv2.dtos.UserResponseDto;
import com.scaler.springtaskmgrv2.entities.UserEntity;
import com.scaler.springtaskmgrv2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto user) {

        var createdUser = userService.createUser(user);
        var userResponseDto = modelMapper.map(createdUser, UserResponseDto.class);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/auth/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginUserDto loginUserPayload) {

        var userResponse = userService.attemptToLogin(loginUserPayload);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/auth/check/availability/{userId}")
    public ResponseEntity<String> checkAvailability(@PathVariable String userId) {
        return ResponseEntity.ok(userId + " is not Available, please sign up");
    }
    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> listAllUsers() {
        return null;
    }
}
