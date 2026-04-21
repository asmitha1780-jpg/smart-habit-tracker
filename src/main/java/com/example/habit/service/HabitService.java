package com.example.habit.service;

import com.example.habit.dto.HabitResponse;

import java.util.List;

public interface HabitService {

    HabitResponse createHabit(String name, String description, Long userId);
    List<HabitResponse> getHabitsByUser(Long userId);
    
}