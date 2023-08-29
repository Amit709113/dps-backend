package com.amit.dps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dps.payloads.ApiResponse;
import com.amit.dps.payloads.NoticeDto;
import com.amit.dps.services.NoticeService;

@RestController
@RequestMapping("api/notices/")
@CrossOrigin(origins = "*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//creating notice
	@PostMapping("/")
	public ResponseEntity<NoticeDto> createNotice(@RequestBody NoticeDto noticeDto){
		NoticeDto createdNotice = this.noticeService.createNotice(noticeDto);
		return new ResponseEntity<NoticeDto>(createdNotice ,HttpStatus.OK);
	}
	
	//updating notice
	@PutMapping("/{noticeId}")
	public ResponseEntity<NoticeDto> updateNotice(@RequestBody NoticeDto noticeDto,@PathVariable Integer noticeId){
		NoticeDto updatedNotice=this.noticeService.updateNotice(noticeDto, noticeId);
		return new ResponseEntity<NoticeDto>(updatedNotice,HttpStatus.OK);
	}
	//get notice by id
	@GetMapping("/{noticeId}")
	public ResponseEntity<NoticeDto> getNoticeById(@PathVariable Integer noticeId){
		NoticeDto noticeDto=this.noticeService.getNoticeById(noticeId);
		return ResponseEntity.ok(noticeDto);
	}
	
	//get all notice
	@GetMapping("/")
    public ResponseEntity<List<NoticeDto>> getAllNotice(){
		List<NoticeDto> list=this.noticeService.getAllNotice();
		return new ResponseEntity<List<NoticeDto>>(list,HttpStatus.OK);
		
}
	
	//delete notice
	@DeleteMapping("/{noticeId}")
	public ResponseEntity<ApiResponse> deleteNotice(@PathVariable Integer noticeId){
		this.noticeService.deleteNotice(noticeId);
		return new ResponseEntity<ApiResponse>( new ApiResponse("notice is deleted successfully",true),HttpStatus.ACCEPTED);
		
	}

}
