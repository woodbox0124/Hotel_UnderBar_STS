package com.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.ReviewDTO;
import com.dto.UploadDTO;
import com.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService service;

	@RequestMapping("/ReviewWrite") //리뷰쓰기
	public String reviewlist(@RequestParam("hotelname") String hotelname,RedirectAttributes attr){
		attr.addFlashAttribute("hotelname",hotelname);
		return "review/ReviewWrite";
	}
	@RequestMapping("/Reviewinsert") //리뷰정보들 sql에 insert하고 사진 지정폴더에 저장시켜주기ㄴ
	public String reviewinsert(UploadDTO dto,RedirectAttributes attr) { //자동주입
		String u_id=dto.getU_id();
		String content=dto.getContent();
		int rating=dto.getStar();
		String title=dto.getTitle();
		String hotelname=dto.getHotelname();
		CommonsMultipartFile theFile= dto.getTheFile();
		long size = theFile.getSize();
		String name= theFile.getName();
		String originalFileName= theFile.getOriginalFilename();
		String contentType= theFile.getContentType();
		System.out.println("내용 "+content);
		System.out.println("파일"+theFile);
		System.out.println("================");
		System.out.println("size:  "+ size);
		System.out.println("name:  "+ name);
		System.out.println("originalFileName:  "+ originalFileName);
		System.out.println("contentType:  "+ contentType);
		System.out.println("정보들======="+u_id+content+rating+title+hotelname);
		
		
		return "";
	}
	
}
