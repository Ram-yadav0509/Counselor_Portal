package com.ashokit.services;

import com.ashokit.dto.CounsellorDTO;

public interface CounsellorService {
	
	public CounsellorDTO login(String email, String password);
	
	public boolean isUniqueEmail(String email);
	
	public boolean register(CounsellorDTO counsellorDTO);

}
