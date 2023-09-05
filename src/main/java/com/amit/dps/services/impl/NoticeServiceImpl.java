package com.amit.dps.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dps.entities.Notice;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.payloads.NoticeDto;
import com.amit.dps.repositories.NoticeRepo;
import com.amit.dps.services.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private NoticeRepo noticeRepo;
	
	
	//create a notice
	@Override
	public NoticeDto createNotice(NoticeDto noticeDto) {
		
		Notice notice = this.modelMapper.map(noticeDto, Notice.class);
		Notice savedNotice = this.noticeRepo.save(notice);
		NoticeDto savedNoticeDto = this.modelMapper.map(savedNotice, NoticeDto.class);
		
		return savedNoticeDto;
	}

	//update a notice
	@Override
	public NoticeDto updateNotice(NoticeDto noticeDto, Integer noticeId) {
		
		Notice notice = this.noticeRepo.findById(noticeId).orElseThrow(()->new ResourceNotFoundException("notice", "noticeId", noticeId));
		
		if(noticeDto.getNoticeAuthor()!=null) {
			notice.setNoticeAuthor(noticeDto.getNoticeAuthor());
			
		}
        if(noticeDto.getNoticeDate()!=null) {
        	notice.setNoticeDate(noticeDto.getNoticeDate());
			
		}
        if(noticeDto.getNoticeTitle()!=null) {
        	notice.setNoticeTitle(noticeDto.getNoticeTitle());
			
		}
        if(noticeDto.getNoticeDesc()!=null) {
        	notice.setNoticeDesc(noticeDto.getNoticeDesc());
			
		}
        if(noticeDto.getNoticeLink()!=null) {
        	notice.setNoticeLink(noticeDto.getNoticeLink());
			
		}
        Notice updatedNotice= this.noticeRepo.save(notice);
        NoticeDto updatedNoticeDto=this.modelMapper.map(updatedNotice, NoticeDto.class);
		
		return updatedNoticeDto;
	}

	//get notice by id
	@Override
	public NoticeDto getNoticeById(Integer noticeId) {
		Notice notice= this.noticeRepo.findById(noticeId).orElseThrow(()-> new ResourceNotFoundException("notice", "noticeId", noticeId));
		
		return this.modelMapper.map(notice, NoticeDto.class);
	}

	//get all notice
	@Override
	public List<NoticeDto> getAllNotice() {
		List<Notice> list = this.noticeRepo.findAll();
		List<NoticeDto> noticeDtos= list.stream().map((notice)->this.modelMapper.map(notice, NoticeDto.class)).collect(Collectors.toList());
		return noticeDtos;
	}

	//delete notice by id
	@Override
	public void deleteNotice(Integer noticeId) {
		Notice notice=this.noticeRepo.findById(noticeId).orElseThrow(()->new ResourceNotFoundException("notice","notice id", noticeId));
		this.noticeRepo.delete(notice);

	}

}
