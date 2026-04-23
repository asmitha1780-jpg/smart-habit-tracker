package com.example.habit.service;

import com.example.habit.dto.UserOverviewResponse;
import com.example.habit.entity.UserEntity;
import com.example.habit.exception.ResourceNotFoundException;
import com.example.habit.repository.HabitRepository;
import com.example.habit.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    public UserServiceImpl(UserRepository userRepository,HabitRepository habitRepository) {
        this.userRepository = userRepository;
        this.habitRepository=habitRepository;
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
    
    
    @Override
    public UserOverviewResponse getUserOverview(Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        int totalHabits = habitRepository.countByUserId(userId);
        int activeHabits = habitRepository.countByUserIdAndActiveTrue(userId);

        String level;
        if (activeHabits == 0) {
            level = "LOW";
        } else if (activeHabits <= 2) {
            level = "MEDIUM";
        } else {
            level = "HIGH";
        }

        String message;
        if (activeHabits == 0) {
            message = "No active items available. Consider enabling participation.";
        } else {
            message = "User shows an active pattern with ongoing engagement.";
        }

        return new UserOverviewResponse(
                user.getId(),
                totalHabits,
                activeHabits,
                level,
                message
        );
    }
}
