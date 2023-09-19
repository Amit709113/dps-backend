package com.amit.dps.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="notice_table")
@Getter
@Setter
@NoArgsConstructor

public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer noticeId;
	
	//to me care full here
	@DateTimeFormat
	private String noticeDate;
	
	private String noticeTitle;
	
	private String noticeDesc;
	
	private String noticeAuthor;
	
	private String noticeLink;
	
	//noticeType
	
	
	

}
