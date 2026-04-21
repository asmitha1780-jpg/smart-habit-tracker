package com.example.habit.dto;

public record HabitResponse(
        Long id,
        String name,
        String description,
        boolean active
) {}
