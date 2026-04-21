package com.example.habit.controller;

import com.example.habit.dto.CreateUserRequest;
import com.example.habit.entity.UserEntity;
import com.example.habit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserEntity createUser(@Valid @RequestBody CreateUserRequest request) 
    {
        return userService.createUser(request.name(),request.email());
    }
}
