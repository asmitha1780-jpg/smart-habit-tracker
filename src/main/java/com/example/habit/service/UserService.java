package com.example.habit.service;

import com.example.habit.dto.UserOverviewResponse;
import com.example.habit.entity.UserEntity;

public interface UserService 

{
    UserEntity createUser(String name, String email);
    UserEntity getUserById(Long userId);
    UserOverviewResponse getUserOverview(Long userId);
}
