package com.example.habit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class UserEntity 
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable=false,updatable=false)
    private LocalDateTime createdAt;

	public UserEntity() {
		super();
	}


      public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
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
    
    public String getEmail() 
    {
    	return email; 
    }
    
    public LocalDateTime getCreatedAt() 
    {
    	return createdAt; 
    }
}
