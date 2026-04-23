package com.example.habit.controller;

import com.example.habit.dto.CreateHabitRequest;
import com.example.habit.dto.FeedbackResponse;
import com.example.habit.dto.HabitResponse;
import com.example.habit.dto.HabitSummaryResponse;
import com.example.habit.dto.WeeklySummaryResponse;
import com.example.habit.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) 
    {
        this.habitService = habitService;
    }

    @PostMapping
    public HabitResponse createHabit(@Valid @RequestBody CreateHabitRequest request)
    {
        return habitService.createHabit(
                request.name(),
                request.description(),
                request.userId()
        );
    }

    @GetMapping("/user/{userId}")
    public List<HabitResponse> getHabitsByUser(@PathVariable Long userId) {
        return habitService.getHabitsByUser(userId);
    }
    
    
    @PostMapping("/{habitId}/check-in")
    public void checkInHabit(@PathVariable Long habitId)
    {
    	habitService.checkInHabit(habitId);
    }
    
    
    @GetMapping("/{habitId}/summary")
    public HabitSummaryResponse getHabitSummary(@PathVariable Long habitId) 
    {
        return habitService.getHabitSummary(habitId);
    }
    

    @GetMapping("/{habitId}/weekly-summary")
    public WeeklySummaryResponse getWeeklySummary(@PathVariable Long habitId) 
    {
       return habitService.getWeeklySummary(habitId);
    }
    
    
    @GetMapping("/{habitId}/feedback")
    public FeedbackResponse getFeedback(@PathVariable Long habitId) 
    {
        return habitService.getFeedback(habitId);
    }


}