package com.example.habit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="habits")
public class HabitEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String description;

    @Column(nullable=false)
    private boolean active;

    @Column(nullable=false,updatable=false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="user_id")
    private UserEntity user;

	public HabitEntity() {
		super();
	}


   public HabitEntity(String name, String description, UserEntity user) 
   {
        this.name = name;
        this.description = description;
        this.user = user;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId()
    {
    	return id; 
    }
    
    public String getName() 
    {
    	return name; 
    }
    
    public String getDescription() 
    {
    	return description; 
    }
    
    public boolean isActive() 
    {
    	return active; 
    }
    
    public UserEntity getUser() 
    {
    	return user;
    }   

}