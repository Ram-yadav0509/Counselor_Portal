package com.ashokit.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.dto.CounsellorDTO;
import com.ashokit.entites.Counsellor;
import com.ashokit.repository.CounsellorRepo;
import com.ashokit.services.CounsellorService;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepo counsellorRepo;

	@Override
	public CounsellorDTO login(String email, String password) {
		Counsellor entity = counsellorRepo.findByEmailAndPassword(email, password);
		if (entity != null) {
			CounsellorDTO dto = new CounsellorDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		Counsellor existing = counsellorRepo.findByEmail(email);
		return  existing == null;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {		
		Counsellor entity = new Counsellor();
		BeanUtils.copyProperties(counsellorDTO, entity);
		Counsellor savedEntity = counsellorRepo.save(entity);
		return savedEntity.getCounsellorId() != null;
	}
}
