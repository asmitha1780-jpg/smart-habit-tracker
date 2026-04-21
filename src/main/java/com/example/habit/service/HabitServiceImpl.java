package com.example.habit.service;

import com.example.habit.dto.HabitResponse;
import com.example.habit.entity.HabitEntity;
import com.example.habit.entity.UserEntity;
import com.example.habit.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final UserService userService;

    public HabitServiceImpl(HabitRepository habitRepository,
                            UserService userService) {
        this.habitRepository = habitRepository;
        this.userService = userService;
    }

    @Override
    public HabitResponse createHabit(String name, String description, Long userId) {
        UserEntity user = userService.getUserById(userId);
        HabitEntity habit = new HabitEntity(name, description, user);
        HabitEntity saved = habitRepository.save(habit);

        return new HabitResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.isActive()
        );
    }

    @Override
    public List<HabitResponse> getHabitsByUser(Long userId) {
        return habitRepository.findByUserId(userId).stream()
                .map(h -> new HabitResponse(
                        h.getId(),
                        h.getName(),
                        h.getDescription(),
                        h.isActive()))
                .toList();
    }
}
