package com.example.College.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.College.DTO.College;

public interface CollegeRepository extends JpaRepository<College,Integer>
{

}
