package com.example.habit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateHabitRequest(
        @NotBlank String name,
        String description,
        @NotNull Long userId
) {}