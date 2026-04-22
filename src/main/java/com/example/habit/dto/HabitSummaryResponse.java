package com.example.habit.dto;

public record HabitSummaryResponse(
        Long habitId,
        int currentStreak,
        String consistencyLevel
) {}