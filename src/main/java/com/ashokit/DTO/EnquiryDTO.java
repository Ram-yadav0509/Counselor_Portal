package com.ashokit.DTO;

import lombok.Data;

@Data
public class EnquiryDTO {

	private Integer enquiryId;
	private String name;
	private Long phoneNo;
	private String courseName;
	private String classMode;
	private String enqStatus;

}
