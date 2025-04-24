package com.ashokit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.DTO.CounsellorDTO;
import com.ashokit.repository.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	CounsellorRepo counsellorRepo;
	
	@Override
	public CounsellorDTO login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUniqueEmail(String email, Integer counsellorId) {
		return false;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
