package com.ashokit.services;

import com.ashokit.DTO.CounsellorDTO;

public interface CounsellorService {
	
	public CounsellorDTO login(String email, String password);
	
	public boolean isUniqueEmail(String email, Integer counsellorId);
	
	public boolean register(CounsellorDTO counsellorDTO);

}
