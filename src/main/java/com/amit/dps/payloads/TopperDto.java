package com.amit.dps.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopperDto {
	private Integer topperId;
	private Float topperScore;
	private String topperName;
	private String topperFeedback;
	private String topperYear;
	private String topperLink;
}
