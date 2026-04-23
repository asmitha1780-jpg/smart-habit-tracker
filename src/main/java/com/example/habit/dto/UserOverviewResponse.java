package com.example.habit.dto;

public record UserOverviewResponse(
        Long userId,
        int totalHabits,
        int activeHabits,
        String consistencyLevel,
        String message
) {}
