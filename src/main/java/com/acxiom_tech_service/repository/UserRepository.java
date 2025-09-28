package com.acxiom_tech_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acxiom_tech_service.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
    Optional<UserEntity> findByUserName(String username);
    
}