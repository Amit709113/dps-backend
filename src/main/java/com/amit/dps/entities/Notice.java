package com.amit.dps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	private String author;
	
	private String noticeLink;
	
	//noticeType
	
	
	

}
