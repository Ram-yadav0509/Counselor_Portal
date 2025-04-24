package com.ashokit.entites;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COUNSELLOR")
@Setter
@Getter
public class Counsellor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNSELLOR_ID")
	private Integer counselloId;

	@Column(name = "COUNSELLOR_NAME")
	private String counsellorName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PHONE_NO")
	private Long phoneNo;

	@CreationTimestamp
	@Column(name = "DATE_CREATED")
	private LocalDate dataCreated;

}
