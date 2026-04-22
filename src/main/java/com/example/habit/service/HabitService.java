package com.example.habit.service;

import com.example.habit.dto.HabitResponse;
import com.example.habit.dto.HabitSummaryResponse;

import java.util.List;

public interface HabitService {

    HabitResponse createHabit(String name, String description, Long userId);
    List<HabitResponse> getHabitsByUser(Long userId);
    void checkInHabit(Long habitId);
    HabitSummaryResponse getHabitSummary(Long habitId);

    
}