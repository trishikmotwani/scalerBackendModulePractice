package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.dtos.CreateUserDto;
import com.scaler.springtaskmgrv2.dtos.LoginUserDto;
import com.scaler.springtaskmgrv2.dtos.UserResponseDto;
import com.scaler.springtaskmgrv2.entities.UserEntity;
import com.scaler.springtaskmgrv2.repositories.UsersRepository;
import com.scaler.springtaskmgrv2.services.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtService jwtService;

    public UserEntity createUser(CreateUserDto createUserPayload) {
        createUserPayload.setPassword(bcryptEncoder.encode(createUserPayload.getPassword()));
        UserEntity user = modelMapper.map(createUserPayload, UserEntity.class);
        var savedUser = usersRepository.save(user);
        return savedUser;
    }

    public UserResponseDto attemptToLogin(LoginUserDto loginUserPayload) {
        var userEntity = usersRepository.findByUsername(loginUserPayload.getUsername());
        if(userEntity == null) {
            throw new UserNotFoundException(userEntity.getId());
        }
        boolean isCorrectPassword = bcryptEncoder.matches(loginUserPayload.getPassword(), userEntity.getPassword());
        if(!isCorrectPassword) {
            throw new UserNotFoundException(userEntity.getId());
            // dont throw below exception and try to allow attackers know that password was wrong
//            throw new IncorrectPasswordException();
        }
        String userJwtToken = jwtService.createJwtToken(userEntity.getId());
        UserResponseDto userResponse = modelMapper.map(userEntity, UserResponseDto.class);
        userResponse.setToken(userJwtToken);
        return userResponse;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        UserNotFoundException(Integer id) {
            super("User with id "  + id + " not found.");
        }
        UserNotFoundException(String username) {
            super("User with username" + username + " not found.");
        }
    }

    public static class IncorrectPasswordException extends IllegalArgumentException {
        IncorrectPasswordException() {
            super("Wrong Password");
        }
    }
}
