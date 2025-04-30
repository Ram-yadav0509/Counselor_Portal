package com.ashokit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.dto.EnquiryDTO;
import com.ashokit.serviceImpl.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryServiceImpl enquiryService;

	@GetMapping("/enquiry/add")
	public String addEnquiry(Model model) {
		EnquiryDTO dto = new EnquiryDTO();
		model.addAttribute("enquiryDTO", dto);
		return "addEnquiry";
	}

	@PostMapping("/enquiry/add")
	public String handleAddEnquiry(EnquiryDTO dto, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("CounsellorId");
		boolean status = enquiryService.upsertEnquiry(dto, counsellorId);
		if (status) {
			model.addAttribute("success", "Enquiry Saved Successfully");
		}
		
		model.addAttribute("enquiryDTO", new EnquiryDTO());
		return "addEnquiry";
	}

	@GetMapping("/enquiries")
	public String getAllEnquires(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);		
		Integer counsellorId = (Integer) session.getAttribute("CounsellorId");
		List<EnquiryDTO> allEnquires = enquiryService.getEnquiries(counsellorId);
		model.addAttribute("allEnquires", allEnquires);		
		EnquiryDTO filter = new EnquiryDTO();
		model.addAttribute("filterDTO", filter);
		
		return "viewEnquires";
	}
	
	@PostMapping("/filter-enq")
	public String filterEnquires(@ModelAttribute("filterDTO") EnquiryDTO enquiryDTO, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("CounsellorId");
		List<EnquiryDTO> filterEnqs = enquiryService.filterEnqs(enquiryDTO, counsellorId);
		model.addAttribute("allEnquires", filterEnqs);
		return "viewEnquires";
	}

}
