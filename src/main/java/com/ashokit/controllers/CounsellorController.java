package com.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.dto.CounsellorDTO;
import com.ashokit.dto.DashboardDTO;
import com.ashokit.serviceImpl.CounsellorServiceImpl;
import com.ashokit.serviceImpl.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private CounsellorServiceImpl counsellorService;

	@Autowired
	private EnquiryServiceImpl enquiryService;

	@GetMapping("/")
	public String index(Model model) {
		CounsellorDTO dto = new CounsellorDTO();
		model.addAttribute("counsellorDTO", dto);
		return "index";
	}

	@PostMapping("/login")
	public String handelLogin(Model model, HttpServletRequest request, CounsellorDTO dto) {
		CounsellorDTO login = counsellorService.login(dto.getEmail(), dto.getPassword());
		if (login == null) {
			model.addAttribute("errorMsg", "Invalid Credentials");
			return "index";
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("CounsellorId", login.getCounsellorId());
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/";
		}
		Integer counsellorId = (Integer) session.getAttribute("CounsellorId");
		if (counsellorId == null) {
			return "redirect:/";
		}
		DashboardDTO dashboardInfo = enquiryService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboard", dashboardInfo);
		return "dashboard";

	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
 
	@GetMapping("/register")
	public String register(Model model) {
		CounsellorDTO dto = new CounsellorDTO();
		model.addAttribute("counsellorDTO", dto);
		return "register";
	}

	@PostMapping("/register")
	public String handleRegister(Model model, @ModelAttribute("counsellorDTO") CounsellorDTO counsellorDTO) {
		boolean status = counsellorService.isUniqueEmail(counsellorDTO.getEmail());
		if (status) {
			boolean register = counsellorService.register(counsellorDTO);
			if (register) {
				model.addAttribute("successMsg", "Registration Success.");
			} else {
				model.addAttribute("failedMsg", "Registration Failed");
			}
		} else {
			model.addAttribute("errorMsg", "Email is already in use.");
		}
		return "register";
	}

}
