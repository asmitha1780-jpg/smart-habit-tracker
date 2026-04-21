package com.example.habit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.habit.entity.HabitEntity;

public interface HabitRepository extends JpaRepository<HabitEntity,Long> {
	List<HabitEntity> findByUserId(Long userId);

}
