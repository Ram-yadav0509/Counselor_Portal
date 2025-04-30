package com.ashokit.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.dto.DashboardDTO;
import com.ashokit.dto.EnquiryDTO;
import com.ashokit.entites.Counsellor;
import com.ashokit.entites.Enquiry;
import com.ashokit.repository.CounsellorRepo;
import com.ashokit.repository.EnquiryRepo;
import com.ashokit.services.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	EnquiryRepo enquiryRepo;

	@Autowired
	CounsellorRepo counsellorRepo;

	@Override
	public DashboardDTO getDashboardInfo(Integer counsellorId) {
		Counsellor counsellorEntity = new Counsellor();
		counsellorEntity.setCounsellorId(counsellorId);

		Enquiry enquiryEntity = new Enquiry();
		enquiryEntity.setCounsellor(counsellorEntity);

		List<Enquiry> enquiryList = enquiryRepo.findAll(Example.of(enquiryEntity));
		int total = enquiryList.size();
		int open = enquiryList.stream().filter(e -> e.getEnquiryStatus().equalsIgnoreCase("OPEN"))
				.collect(Collectors.toList()).size();
		int enrolled = enquiryList.stream().filter(e -> e.getEnquiryStatus().equalsIgnoreCase("ENROLLED"))
				.collect(Collectors.toList()).size();
		int lost = enquiryList.stream().filter(e -> e.getEnquiryStatus().equalsIgnoreCase("LOSE"))
				.collect(Collectors.toList()).size();

//		DashboardDTO dto = new DashboardDTO();
//		dto.setEnrolledEnqs(enrolled);
//		dto.setLostEnqs(lose);
//		dto.setOpenEnqs(open);
//		dto.setTotalEnqs(total);

		return DashboardDTO.builder().totalEnqs(total).openEnqs(open).lostEnqs(lost).enrolledEnqs(enrolled).build();
	}

	@Override
	public boolean upsertEnquiry(EnquiryDTO enqDto, Integer counsellorId) {
		Enquiry entity = new Enquiry();
		BeanUtils.copyProperties(enqDto, entity);
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		entity.setCounsellor(counsellor);
		Enquiry savedEnquiry = enquiryRepo.save(entity);
		return savedEnquiry.getEnquiryId() != null;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(Integer counsellorId) {
		List<Enquiry> enquiryList = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		List<EnquiryDTO> dtoList = new ArrayList<>();
		enquiryList.forEach(e -> {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(e, dto);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public List<EnquiryDTO> filterEnqs(EnquiryDTO filterDto, Integer counsellorId) {
		Enquiry entity = new Enquiry();
		if (filterDto.getClassMode() != null && !filterDto.getClassMode().equals("")) {
			entity.setClassMode(filterDto.getClassMode());
		}
		if (filterDto.getCourseName() != null && !filterDto.getCourseName().equals("")) {
			entity.setCourseName(filterDto.getCourseName());
		}
		if (filterDto.getEnquiryStatus() != null && !filterDto.getEnquiryStatus().equals("")) {
			entity.setEnquiryStatus(filterDto.getEnquiryStatus());
		}

		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		entity.setCounsellor(counsellor);

		List<Enquiry> list = enquiryRepo.findAll(Example.of(entity));

		List<EnquiryDTO> dtoList = new ArrayList<>();
		list.forEach(e -> {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(e, dto);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public EnquiryDTO getEnquiry(Integer enqId) {
		Optional<Enquiry> optional = enquiryRepo.findById(enqId);
		if (optional.isPresent()) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(optional.get(), dto);
			return dto;
		}
		return null;
	}

}
