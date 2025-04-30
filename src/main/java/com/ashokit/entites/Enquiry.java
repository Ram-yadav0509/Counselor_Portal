package com.ashokit.entites;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "ENQUIRY")
@Setter
@Getter
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ENQUIRY_ID")
	private Integer enquiryId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PHONE")
	private Long phone;
	
	@Column(name = "COURSE_NAME")
	private String courseName;
	
	@Column(name = "CLASS_MODE")
	private String classMode;
	
	@Column(name = "ENQ_STATUS")
	private String enquiryStatus;
	
	@CreationTimestamp
	@Column(name = "DATE_CREATED")
	private LocalDate createdAt;
	
	@UpdateTimestamp
	@Column(name = "DATE_UPDATED")
	private LocalDate updatedAt;
		
	@ManyToOne
	@JoinColumn(name = "COUNSELLOR_ID")
	private Counsellor counsellor;

}
