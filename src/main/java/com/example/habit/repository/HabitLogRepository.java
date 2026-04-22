package com.example.habit.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.habit.entity.HabitLogEntity;

public interface HabitLogRepository extends JpaRepository<HabitLogEntity,Long> {
	
	boolean existsByHabitIdAndDate(Long habitId,LocalDate date);
	
	List<HabitLogEntity> findByHabitIdOrderByDateDesc(Long habitId);

}
