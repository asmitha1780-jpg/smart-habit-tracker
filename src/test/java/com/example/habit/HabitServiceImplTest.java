package com.example.habit;

import com.example.habit.dto.HabitSummaryResponse;
import com.example.habit.entity.HabitEntity;
import com.example.habit.entity.HabitLogEntity;
import com.example.habit.repository.HabitLogRepository;
import com.example.habit.repository.HabitRepository;
import com.example.habit.service.HabitServiceImpl;
import com.example.habit.service.InsightMessageService;
import com.example.habit.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HabitServiceImplTest {

    @Mock
    private HabitRepository habitRepository;

    @Mock
    private HabitLogRepository habitLogRepository;

    @Mock
    private UserService userService;

    @Mock
    private InsightMessageService insightMessageService;

    
    @InjectMocks
    private HabitServiceImpl habitService;


    @Test
    void shouldReturnZeroStreakWhenNoLogs() {

        HabitEntity habit = new HabitEntity("Test Habit", "Test Description", null);

        when(habitRepository.findById(1L))
                .thenReturn(Optional.of(habit));

        when(habitLogRepository.findByHabitIdOrderByDateDesc(1L))
                .thenReturn(List.of());

        HabitSummaryResponse summary =
                habitService.getHabitSummary(1L);

        assertEquals(0, summary.currentStreak());
    }

    
    @Test
    void shouldCalculateStreakForConsecutiveDays() {

        HabitEntity habit = new HabitEntity("Test Habit", "Test Description", null);

        List<HabitLogEntity> logs = List.of(
                new HabitLogEntity(habit, LocalDate.now()),
                new HabitLogEntity(habit, LocalDate.now().minusDays(1)),
                new HabitLogEntity(habit, LocalDate.now().minusDays(2))
        );

        when(habitRepository.findById(1L))
                .thenReturn(Optional.of(habit));

        when(habitLogRepository.findByHabitIdOrderByDateDesc(1L))
                .thenReturn(logs);

        HabitSummaryResponse summary=habitService.getHabitSummary(1L);

        assertEquals(3, summary.currentStreak());
    }
}
