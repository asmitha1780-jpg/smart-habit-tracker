package com.example.habit.service;

import com.example.habit.dto.HabitResponse;
import com.example.habit.dto.HabitSummaryResponse;
import com.example.habit.entity.HabitEntity;
import com.example.habit.entity.HabitLogEntity;
import com.example.habit.entity.UserEntity;
import com.example.habit.exception.DuplicateHabitLogException;
import com.example.habit.exception.ResourceNotFoundException;
import com.example.habit.repository.HabitLogRepository;
import com.example.habit.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final UserService userService;
    private final HabitLogRepository habitLogRepository;

    public HabitServiceImpl(HabitRepository habitRepository,UserService userService,HabitLogRepository habitLogRepository) 
    {
        this.habitRepository=habitRepository;
        this.userService=userService;
        this.habitLogRepository=habitLogRepository;
    }

    @Override
    public HabitResponse createHabit(String name, String description, Long userId) 
    {
        UserEntity user=userService.getUserById(userId);
        HabitEntity habit=new HabitEntity(name, description, user);
        HabitEntity saved=habitRepository.save(habit);

        return new HabitResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.isActive()
        );
    }

    @Override
    public List<HabitResponse> getHabitsByUser(Long userId) 
    {
        return habitRepository.findByUserId(userId).stream()
                .map(h -> new HabitResponse(
                        h.getId(),
                        h.getName(),
                        h.getDescription(),
                        h.isActive()))
                .toList();
    }
    
    

    @Override
    public void checkInHabit(Long habitId) 
    {
        HabitEntity habit=habitRepository.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        LocalDate today=LocalDate.now();

        boolean alreadyCheckedIn=habitLogRepository.existsByHabitIdAndDate(habitId,today);
        
        if (alreadyCheckedIn) 
        {
            throw new DuplicateHabitLogException("Habit already checked in for today");
        }

        HabitLogEntity log = new HabitLogEntity(habit,today);
        habitLogRepository.save(log);

    }
    
    
    @Override
    public HabitSummaryResponse getHabitSummary(Long habitId) 
    {
        var habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        var logs = habitLogRepository.findByHabitIdOrderByDateDesc(habitId);

        int streak = calculateCurrentStreak(logs);

        String level = switch (streak) {
            case 0, 1 -> "LOW";
            case 2, 3 -> "MEDIUM";
            default -> "HIGH";
        };

        return new HabitSummaryResponse(
                habit.getId(),
                streak,
                level
        );
    }

    
    private int calculateCurrentStreak(List<HabitLogEntity> logs) 
    {
        if (logs.isEmpty())
        {
            return 0;
        }

        int streak = 0;
        LocalDate expectedDate = LocalDate.now();

        for (HabitLogEntity log : logs) {
            if (log.getDate().equals(expectedDate)) 
            {
                streak++;
                expectedDate = expectedDate.minusDays(1);
            } 
            else 
            {
                break;
            }
        }
        return streak;
    }
    
    
}
