package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dto.EventDTO;
import com.dto.EventPageDTO;
import com.service.EventService;



@Controller
public class EventController {
	@Autowired
	EventService eService;
	//시작점
	
	
	//EVENT 글쓰기 클릭 
	@RequestMapping("/loginCheck/eventWrite")
	public String eve_write() {
		return "redirect:../eventWrite";
	}
	@RequestMapping("/loginCheck/eventDelete")
	public String eve_delete(@RequestParam String code) {
		eService.delete(code);
		return "redirect:../event";
	}
	@RequestMapping("/loginCheck/eventUpdate")
	public String eve_update(EventDTO dto ) {
		eService.update(dto);
		return "redirect:../event";
	}
	
	//EVENT 글쓰기  
	@RequestMapping("/loginCheck/eventInsert")
	public String eve_insert(EventDTO dto, MultipartFile[] uploadFile, Model model) {
		System.out.println("찍히는 dto는 ?"+dto);
		System.out.println(uploadFile);
		//String uploadFolder = "/Users/bitna/Documents/GitHub/Hotel_UnderBar_STS/src/main/webapp/WEB-INF/views/images/uploadImg";
		String uploadFolder = "C:\\Shop_STS\\WORKSHOP\\Hotel_UnderBar_STS\\src\\main\\webapp\\WEB-INF\\views\\images\\uploadImg";
		for(MultipartFile multipartFile : uploadFile) {
			System.out.println("===============");
			System.out.println("upload file name :" + multipartFile.getOriginalFilename());
			System.out.println("upload file size :" + multipartFile.getSize());
			
			dto.setEventImg(multipartFile.getOriginalFilename());
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		eService.eventInsert(dto);
		System.out.println("eventInsert : "+dto);
		return "redirect:../event";
	}
	
	@RequestMapping("/event")
	public ModelAndView event(@RequestParam(required=false, defaultValue="1") String curPage) {
		//event 불러오기 + 페이징
		System.out.println("이벤트 curpage" + curPage);
		EventPageDTO epDTO = eService.eventList(Integer.parseInt(curPage));
		ModelAndView mav = new ModelAndView();
		mav.addObject("epDTO",epDTO);
		mav.setViewName("event");
		return mav;			
	}
	
	@RequestMapping("/eventRetrieve")
	public ModelAndView event_retrieve(@RequestParam String code) {
		//event retrieve
		EventDTO eDTO = eService.eventRetrieve(code);
		System.out.println(eDTO+": event_retrieve");
		ModelAndView mav = new ModelAndView();
		mav.addObject("eDTO",eDTO);
		mav.setViewName("eventRetrieve");
		return mav;			
	}
	
	
	
	
	
	
	
}
