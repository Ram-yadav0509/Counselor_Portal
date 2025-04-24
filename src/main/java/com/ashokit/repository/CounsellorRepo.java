package com.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entites.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	
}
