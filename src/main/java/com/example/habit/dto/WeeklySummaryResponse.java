package com.example.habit.dto;

public record WeeklySummaryResponse(
        Long habitId,
        int daysCompleted,
        int totalDays,
        double successPercentage
) {}
