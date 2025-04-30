package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entites.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
	
	// select * from enquiry_tbl where counsellor_id=:counsellorId;
	public List<Enquiry> findByCounsellorCounsellorId(Integer counsellorId);

}
