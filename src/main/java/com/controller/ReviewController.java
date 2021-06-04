package com.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.dto.ReviewCountDTO;
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
	@RequestMapping("/ReviewAdminUpdate") // 어드민 리뷰 수정
	public String reviewadminup(int num,RedirectAttributes attr){
		attr.addFlashAttribute("num", num);
		return "review/ReviewAdminUpdate";
	}
	@RequestMapping("/ReviewUpdate") //리뷰 수정
	public String reviewupdate(int num,RedirectAttributes attr){
		attr.addFlashAttribute("num", num);
		return "review/ReviewUpdate";
	}
	@RequestMapping("/ReviewAnswer") // 답글 쓰기
	public String reviewanswer(int num, RedirectAttributes attr){
		attr.addFlashAttribute("num", num);
		return "review/ReviewAnswer";
	}
	@RequestMapping(value="/Reviewupload",method = RequestMethod.POST) //리뷰정보들 sql에 insert하고 사진 지정폴더에 저장시켜주기
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
		
		File f= new File("C:\\upload", originalFileName);
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
	
	@RequestMapping(value="/ReviewUpdateUp",method = RequestMethod.POST) 
	public String reviewupdateup(RedirectAttributes attr,String hotelname, int num, UploadDTO dto, HttpSession session,HttpServletRequest request) { //자동주입
		
		String content=dto.getContent();
		String title=dto.getTitle();
		int rating=dto.getStar();
		List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("num", num);
		map.put("rating", rating);
		map.put("content", content);
		map.put("title", title);
		service.reviewUpdateUp(map);
		request.setAttribute("hotelname", hotelname);
		request.setAttribute("mesg", "수정이 완료되었습니다");
		session.setAttribute("reviewcount", reviewcount);
		
		return "review/Reviewend";
		
	}
	@RequestMapping(value="/ReviewAdminUp",method = RequestMethod.POST) 
	public String reviewadminup(String hotelname, int num, UploadDTO dto, HttpSession session,HttpServletRequest request) { //자동주입
		
		String content=dto.getContent();
		String title=dto.getTitle();

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("num", num);
		map.put("content", content);
		map.put("title", title);
		System.out.println(map);
		service.reviewAdminUp(map);
		request.setAttribute("hotelname", hotelname);
		 request.setAttribute("mesg", "수정이 완료되었습니다");
		
		return "review/Reviewend";
		
	}
	
	@RequestMapping(value="/ReviewAnswerUp",method = RequestMethod.POST) 
	public String reviewanswer(String u_id, String hotelname, int num, UploadDTO dto, HttpSession session,HttpServletRequest request) { //자동주입
		
		String content=dto.getContent();
		String title=dto.getTitle();

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("num", num);
		map.put("hotelname", hotelname);
		map.put("content", content);
		map.put("title", title);
		map.put("u_id", u_id);
		System.out.println(map);
		service.reviewAnswerUp(map);
		request.setAttribute("hotelname", hotelname);
		 request.setAttribute("mesg", "답글작성이 완료되었습니다");
		
		return "review/Reviewend";
		
	}
	@RequestMapping("/loginCheck/Review") //호텔에서 평점보기 눌렀을때 넘어가서 뿌려주는 부분
	public String review(String hotelname,HttpSession session, HttpServletRequest request, RedirectAttributes attr){
		List<ReviewDTO> list = service.review(hotelname); //호텔이름에 해당하는 리뷰들 뽑아오기
		List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
	    
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		session.setAttribute("reviewlist", list);
		session.setAttribute("hotelname", hotelname);
		session.setAttribute("admin", admin);
		session.setAttribute("u_id1", u_id1);
		session.setAttribute("reviewcount", reviewcount);
		
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewOrder") //필터 오래된순정렬
	public String reviewOrder(HttpServletRequest request, RedirectAttributes attr, HttpSession session){
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.reviewOrder(hotelname);
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		session.setAttribute("reviewlist", list);
		session.setAttribute("admin", admin);
		session.setAttribute("u_id1", u_id1);
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewNew") //필터 최신순정렬 
	public String reviewNew(HttpServletRequest request, RedirectAttributes attr, HttpSession session){
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.review(hotelname);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		session.setAttribute("reviewlist", list);
		session.setAttribute("admin", admin);
		session.setAttribute("u_id1", u_id1);
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewDelete") 
	public String reviewDelete(int origin, HttpSession session, HttpServletRequest request){
		service.reviewDelete(origin);
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.review(hotelname);
		List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		session.setAttribute("reviewcount", reviewcount);
		session.setAttribute("reviewlist", list);
		session.setAttribute("admin", admin);
		session.setAttribute("u_id1", u_id1);
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewAdminDelete") 
	public String reviewAdminDelete(int num, HttpSession session, RedirectAttributes attr){
		System.out.println(num);
		service.reviewAdminDelete(num);
		
		String hotelname = (String)session.getAttribute("hotelname");
		List<ReviewDTO> list = service.review(hotelname);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
	
		session.setAttribute("reviewlist", list);
		session.setAttribute("admin", admin);
		session.setAttribute("u_id1", u_id1);
		
		return "redirect:../reviewlist";
	}

	
	
}