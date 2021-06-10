package com.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.dto.ReviewCountDTO;
import com.dto.ReviewDTO;
import com.dto.ReviewPageDTO;
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
		
		File f= new File("C:\\Shop_STS\\WORKSHOP\\Hotel_UnderBar_STS\\src\\main\\webapp\\WEB-INF\\views\\images\\review",originalFileName);
		File f2= new File("/Users/bitna",originalFileName);
		ReviewDTO rvdto=new ReviewDTO();
		   rvdto.setU_id(u_id);
		   rvdto.setTitle(title);
		   rvdto.setContent(content);
		   rvdto.setHotelname(hotelname);
		   rvdto.setRating(rating);
		   rvdto.setReview_img(originalFileName); //파일이름
		   System.out.println("리뷰dto에 담은 정보들=============="+rvdto);
		   service.write(rvdto);
		 request.setAttribute("mesg", "리뷰작성이 완료되었습니다");
		 
		try {
			theFile.transferTo(f);
			theFile.transferTo(f2);
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
		CommonsMultipartFile theFile= dto.getTheFile();
		long size = theFile.getSize();
		String name= theFile.getName();
		String originalFileName= theFile.getOriginalFilename();
		String contentType= theFile.getContentType();
		List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
	
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		
		File f= new File("C:\\Shop_STS\\WORKSHOP\\Hotel_UnderBar_STS\\src\\main\\webapp\\WEB-INF\\views\\images\\review",originalFileName);
		map.put("num", num);
		map.put("rating", rating);
		map.put("content", content);
		map.put("title", title);
		map.put("review_img", originalFileName);
		service.reviewUpdateUp(map);
		request.setAttribute("hotelname", hotelname);
		request.setAttribute("mesg", "수정이 완료되었습니다");
		session.setAttribute("reviewcount", reviewcount);
	
		
		try {
			theFile.transferTo(f);
		}catch (Exception e) {
			e.printStackTrace();
		}
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
	public String review(@RequestParam(required=false, defaultValue="1") String curPage, String hotelname,HttpSession session, HttpServletRequest request, RedirectAttributes attr){
		
		ReviewPageDTO rDTO = service.review(Integer.parseInt(curPage), hotelname);
		
		  List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
		  int sumcount=service.sumcount(hotelname); //리뷰 총갯수
		  
		  
		  
		  MemberDTO dto = (MemberDTO) session.getAttribute("login"); 
		  int admin = dto.getAdmin(); 
		  String u_id1 = dto.getU_id();
		  
		  session.setAttribute("reviewlist", rDTO); 
		  session.setAttribute("hotelname",hotelname); 
		  session.setAttribute("admin", admin);
		  session.setAttribute("u_id1", u_id1); 
		  session.setAttribute("reviewcount",reviewcount); session.setAttribute("sumcount", sumcount);
		 
	
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewOrder") //필터 오래된순정렬
	public String reviewOrder(@RequestParam(required=false, defaultValue="1") String curPage, HttpServletRequest request, RedirectAttributes attr, HttpSession session){
		
		String hotelname = (String)session.getAttribute("hotelname");
		ReviewPageDTO rDTO = service.reviewOrder(Integer.parseInt(curPage), hotelname);
		 List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
		  int sumcount=service.sumcount(hotelname); //리뷰 총갯수
		  
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		session.setAttribute("reviewlist", rDTO); 
		  session.setAttribute("hotelname",hotelname); 
		  session.setAttribute("admin", admin);
		  session.setAttribute("u_id1", u_id1); 
		  session.setAttribute("reviewcount",reviewcount); 
		  session.setAttribute("sumcount", sumcount);
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewNew") //필터 최신순정렬 
	public String reviewNew(@RequestParam(required=false, defaultValue="1") String curPage,HttpServletRequest request, RedirectAttributes attr, HttpSession session){
		
		String hotelname = (String)session.getAttribute("hotelname");
		ReviewPageDTO rDTO = service.review(Integer.parseInt(curPage), hotelname);
		
		  List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
		  int sumcount=service.sumcount(hotelname); //리뷰 총갯수
		  
		  MemberDTO dto = (MemberDTO) session.getAttribute("login"); 
		  int admin = dto.getAdmin(); 
		  String u_id1 = dto.getU_id();
		  
		  session.setAttribute("reviewlist", rDTO); 
		  session.setAttribute("hotelname",hotelname); 
		  session.setAttribute("admin", admin);
		  session.setAttribute("u_id1", u_id1); 
		  session.setAttribute("reviewcount",reviewcount); 
		  session.setAttribute("sumcount", sumcount);
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewDelete") 
	public String reviewDelete(@RequestParam(required=false, defaultValue="1") String curPage,int origin, HttpSession session, HttpServletRequest request){
		service.reviewDelete(origin);
		
		String hotelname = (String)session.getAttribute("hotelname");
		ReviewPageDTO rDTO = service.review(Integer.parseInt(curPage), hotelname);
		List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
		 int sumcount=service.sumcount(hotelname); //리뷰 총갯수
		 
		 
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		
		session.setAttribute("reviewlist", rDTO); 
		  session.setAttribute("hotelname",hotelname); 
		  session.setAttribute("admin", admin);
		  session.setAttribute("u_id1", u_id1); 
		  session.setAttribute("reviewcount",reviewcount); 
		  session.setAttribute("sumcount", sumcount);
		
		
		return "redirect:../reviewlist";
	}
	@RequestMapping("/loginCheck/ReviewAdminDelete") 
	public String reviewAdminDelete(@RequestParam(required=false, defaultValue="1") String curPage,int num, HttpSession session, RedirectAttributes attr){
		System.out.println(num);
		service.reviewAdminDelete(num);
		
		String hotelname = (String)session.getAttribute("hotelname");
		ReviewPageDTO rDTO = service.review(Integer.parseInt(curPage), hotelname);
		List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
		 int sumcount=service.sumcount(hotelname); //리뷰 총갯수
		 
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
	
		session.setAttribute("reviewlist", rDTO); 
		  session.setAttribute("hotelname",hotelname); 
		  session.setAttribute("admin", admin);
		  session.setAttribute("u_id1", u_id1); 
		  session.setAttribute("reviewcount",reviewcount); 
		  session.setAttribute("sumcount", sumcount);
		
		return "redirect:../reviewlist";
	}
	
	@RequestMapping("/loginCheck/Reviewrating") //위에 레이팅필터 , rating점수에 맞는 리뷰들만 뽑아오기
	public String reviewrating(@RequestParam(required=false, defaultValue="1") String curPage,int rating,String hotelname,HttpSession session, HttpServletRequest request, RedirectAttributes attr) {
		System.out.println("받아온 리뷰점슈====="+rating);
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("rating",rating);
		map.put("hotelname",hotelname);
		ReviewPageDTO rDTO = service.reviewrating(Integer.parseInt(curPage), map); //호텔이름과 rating에 해당하는 리뷰들 뽑아오기
		List<ReviewCountDTO> reviewcount=service.reviewcount(hotelname); //리뷰점수들 평균내기
	    int sumcount=service.sumcount(hotelname); //리뷰 총갯수
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int admin = dto.getAdmin();
		String u_id1 = dto.getU_id();
		
		session.setAttribute("reviewlist", rDTO); 
		session.setAttribute("hotelname", hotelname);
		session.setAttribute("admin", admin);
		session.setAttribute("u_id1", u_id1);
		session.setAttribute("reviewcount", reviewcount);
		session.setAttribute("sumcount", sumcount);
		return "redirect:../reviewlist";
	}
	
	
}