package com.example.habit.service;

import com.example.habit.entity.UserEntity;
import com.example.habit.exception.ResourceNotFoundException;
import com.example.habit.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(String name,String email) 
    {
        UserEntity user = new UserEntity(name,email);
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUserById(Long userId) 
    {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
