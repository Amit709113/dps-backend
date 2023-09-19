package com.amit.dps.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="topper")
@NoArgsConstructor
@Getter 
@Setter
public class Topper {

	@Id
	@Column(name="topper_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer topperId;
	
	@Column(name="topper_name")
	private String topperName;
	
	@Column(name="topper_feedback",length=500)
	private String topperFeedback;
	
	@Column(name="topper_year")
	private String topperYear;
	
	@Column(name="topper_score")
	private Float topperScore;
	
	@Column(name="topper_link")
	private String topperLink;
	
}
