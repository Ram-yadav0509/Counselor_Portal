package com.ashokit.dto;

import lombok.Data;

@Data
public class EnquiryDTO {

	private Integer enquiryId;
	private String name;
	private Long phone;
	private String courseName;
	private String classMode;
	private String enquiryStatus;

}
