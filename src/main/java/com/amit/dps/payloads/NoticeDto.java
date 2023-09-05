package com.amit.dps.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDto {

	private Integer noticeId;
	
	private String noticeDate;
	
	private String noticeTitle;
	
	private String noticeDesc;
	
	private String noticeLink;
	
	private String noticeAuthor;
	
}
