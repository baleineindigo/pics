package com.devils.pics.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devils.pics.domain.Category;
import com.devils.pics.domain.RepeatDate;
import com.devils.pics.domain.Studio;
import com.devils.pics.domain.StudioFilter;
import com.devils.pics.domain.Tag;
import com.devils.pics.service.ScheduleService;
import com.devils.pics.service.StudioFilterService;
import com.devils.pics.service.StudioInfoService;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@CrossOrigin(origins={"*"}, maxAge=6000)
public class StudioRegisterController {

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private StudioFilterService studioFilterService;
	
	@Autowired
	private StudioInfoService studioInfoService;
	
	@PostMapping("/studio")
	public ResponseEntity registerStudio(@RequestBody Studio studio) {
		StudioFilter studioFilter = studio.getStudioFilter();
		//ArrayList<Tag> tags = studio.getTag();
		//ArrayList<RepeatDate> repeatDates = studio.getSchedule().getRepeatDate();
		
		try {
			/* 세션으로부터 회사 아이디를 받아와서 Studio에 Set */
			//String comId = (String)httpSession.getAttribute("comId");
			System.out.println(studio);
			
			String comId = "com1@gmail.com";
			System.out.println("회사 아이디 : "+ comId);
			studio.setComId(comId);
			
			System.out.println(studio);
			
			/* Studio를 등록 
			   (추후 중복 등록 방지 필요... Studio의 이름, Studio의 주소로 구분해야 할 듯)
			   (중복일 경우 여기에서 끝내야 함) */
			int result = studioInfoService.registerStudioInfo(studio);
			System.out.println("Studio 등록 결과 : "+result);
			
			/* autoIncrement로 생긴 Studio Id를 가져옴  */
			int stdId = studioInfoService.getStudioId(studio);
			System.out.println("autoIncrement로 생긴 Studio Id : "+stdId);
			
			/* StudioFilter에 Studio Id를 set하고, StudioFilter를 등록 */
			studioFilter.setStdId(stdId);
			result = studioFilterService.registerStudioFilter(studioFilter);
			System.out.println("StudioFilter 등록 결과 : "+result);
			
			/* RepeatDate에 Studio Id를 set하고, RepeatDate를 등록 */
			/*
			for(RepeatDate repeatDate : repeatDates) {
				repeatDate.setStuId(stdId);
				result = scheduleService.registerRepeatDate(repeatDate);
			}
			System.out.println("RepeatDate 등록 결과 : "+result);
			*/
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
