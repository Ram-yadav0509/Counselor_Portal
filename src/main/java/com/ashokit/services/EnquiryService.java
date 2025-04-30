package com.ashokit.services;

import java.util.List;

import com.ashokit.dto.DashboardDTO;
import com.ashokit.dto.EnquiryDTO;

public interface EnquiryService {
	
	public DashboardDTO getDashboardInfo(Integer counsellorId);

	public boolean upsertEnquiry(EnquiryDTO enqDto, Integer counsellorId);

	public List<EnquiryDTO> getEnquiries(Integer counsellorId);

	public List<EnquiryDTO> filterEnqs(EnquiryDTO filterDto, Integer counsellorId);

	public EnquiryDTO getEnquiry(Integer enqId);

}
