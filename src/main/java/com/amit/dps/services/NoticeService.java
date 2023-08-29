package com.amit.dps.services;

import java.util.List;

import com.amit.dps.payloads.NoticeDto;

public interface NoticeService {
	NoticeDto createNotice(NoticeDto noticeDto);
	NoticeDto updateNotice(NoticeDto noticeDto, Integer noticeId);
	NoticeDto getNoticeById(Integer noticeId);
	List<NoticeDto> getAllNotice();
	
	void deleteNotice(Integer noticeId);
	//try to write code to delete all the notice at once
	

}
