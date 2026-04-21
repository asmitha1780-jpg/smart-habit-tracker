package com.example.habit.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "habit_logs",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"habit_id", "log_date"})
    }
)
public class HabitLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "habit_id")
    private HabitEntity habit;

    @Column(name = "log_date", nullable = false)
    private LocalDate date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected HabitLogEntity() {}

    public HabitLogEntity(HabitEntity habit,LocalDate date) {
        this.habit = habit;
        this.date = date;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public HabitEntity getHabit() {
        return habit;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}