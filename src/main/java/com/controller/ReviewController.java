package com.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.dto.ReviewDTO;
import com.dto.UploadDTO;
import com.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService service;

	@RequestMapping("/ReviewWrite") //리뷰쓰기
	public String reviewwirte(@RequestParam("hotelname") String hotelname,RedirectAttributes attr){
		attr.addFlashAttribute("hotelname",hotelname);
		return "review/ReviewWrite";
	}
	@RequestMapping("/Reviewupload") //리뷰정보들 sql에 insert하고 사진 지정폴더에 저장시켜주기
	public String reviewinsert(UploadDTO dto,RedirectAttributes attr,HttpServletRequest request) { //자동주입
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
		
		
		File f= new File("c://upload", originalFileName);
		ReviewDTO rvdto=new ReviewDTO();
		   rvdto.setU_id(u_id);
		   rvdto.setTitle(title);
		   rvdto.setContent(content);
		   rvdto.setHotelname(hotelname);
		   rvdto.setRating(rating);
		   rvdto.setReview_img(originalFileName); //파일이름
		   System.out.println("리뷰dto에 담은 정보들=============="+rvdto);
		   service.write(rvdto);
		  //attr.addAttribute("mesg","리뷰작성이 완료되었습니다");
		 request.setAttribute("mesg", "리뷰작성이 완료되었습니다");
		 
		try {
			theFile.transferTo(f);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "review/Reviewend";
		
		
	}
	@RequestMapping("/loginCheck/Review") 
	public String review(String hotelname,HttpSession session, HttpServletRequest request, RedirectAttributes attr){
		List<ReviewDTO> list = service.review(hotelname);
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		session.setAttribute("reviewlist", list);
		session.setAttribute("hotelname", hotelname);
		attr.addFlashAttribute("admin", admin);
		attr.addFlashAttribute("u_id1", u_id1);
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewOrder") 
	public String reviewOrder(HttpServletRequest request, RedirectAttributes attr, HttpSession session){
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.reviewOrder(hotelname);
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		attr.addFlashAttribute("reviewlist", list);
		attr.addFlashAttribute("admin", admin);
		attr.addFlashAttribute("u_id1", u_id1);
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewNew") 
	public String reviewNew(HttpServletRequest request, RedirectAttributes attr, HttpSession session){
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.review(hotelname);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		attr.addFlashAttribute("reviewlist", list);
		attr.addFlashAttribute("admin", admin);
		attr.addFlashAttribute("u_id1", u_id1);
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewDelete") 
	public String reviewDelete(int origin, HttpSession session, RedirectAttributes attr){
		service.reviewDelete(origin);
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.review(hotelname);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		attr.addFlashAttribute("reviewlist", list);
		attr.addFlashAttribute("admin", admin);
		attr.addFlashAttribute("u_id1", u_id1);
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewAdminDelete") 
	public String reviewAdminDelete(int num, HttpSession session, RedirectAttributes attr){
		service.reviewAdminDelete(num);
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.review(hotelname);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		attr.addFlashAttribute("reviewlist", list);
		attr.addFlashAttribute("admin", admin);
		attr.addFlashAttribute("u_id1", u_id1);
		
		return "redirect:../reviewlist";
	}

	
	
}
