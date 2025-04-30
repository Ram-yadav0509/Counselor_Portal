package com.ashokit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardDTO {

	private Integer totalEnqs;
	private Integer openEnqs;
	private Integer enrolledEnqs;
	private Integer lostEnqs;

}
