package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.dto.ResvDTO;
import com.dto.ResvPageDTO;
import com.service.ResvService;
import com.service.RoomService;

@Controller
public class ResvController {

	@Autowired
	ResvService service;
	@Autowired
	RoomService rservice;

	//예약 내역
	@RequestMapping("/loginCheck/resvMy") // Interceptor
	public String myPage(HttpSession session, HttpServletRequest request,RedirectAttributes attr) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

		// 페이징
		String curPage = request.getParameter("curPage");// 현재 페이지 얻기
		if (curPage == null)
			curPage = "1";
		ResvPageDTO RpDTO = null;
		String u_id = dto.getU_id();
		RpDTO = service.resvMyList(Integer.parseInt(curPage), u_id);
		// 페이징 끝

		attr.addFlashAttribute("RpDTO", RpDTO);
		attr.addFlashAttribute("u_id", u_id);
		return "redirect:../resvMy";


	}
	 //예약 취소
	@RequestMapping("/loginCheck/resvCancel")
	public String resvCancel(@RequestParam("seq") int seq) {
		service.resvCancel(seq);
		return "redirect:../";
	}

	@RequestMapping("/loginCheck/checkout")
	public String checkout(@RequestParam("seq") int seq, HttpSession session, HttpServletRequest request,
			RedirectAttributes attr) {
		service.resvCheckout(seq);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		
		// 페이징
		String curPage = request.getParameter("curPage");// 현재 페이지 얻기
		if (curPage == null)
			curPage = "1";
		ResvPageDTO RpDTO = null;
		String u_id = dto.getU_id();
		RpDTO = service.resvMyList(Integer.parseInt(curPage), u_id);
		// 페이징 끝

		attr.addFlashAttribute("RpDTO", RpDTO);
		attr.addFlashAttribute("u_id", u_id);
		return "redirect:../resvMy";
	}
	
	@RequestMapping(value = "/loginCheck/RoomReserv")
	public String RoomReserv(ResvDTO rdto, String hotelname ,String name, String roomseq, int price,
		String hotelseq, HttpSession session, RedirectAttributes attr, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		attr.addFlashAttribute("hotelseq", hotelseq);
		attr.addFlashAttribute("roomseq", roomseq);
		session.setAttribute("hotelname",hotelname);
		session.setAttribute("roomname", name);
		session.setAttribute("price", price);
		
		String checkin = (String)session.getAttribute("checkin");
        String guest = (String)session.getAttribute("guest");
		  HashMap<String, String> map = new HashMap<String, String>();
		  map.put("roomseq", roomseq);
		  map.put("checkin",checkin);
		  int n = rservice.reserved(map);
		  int MaxGuest = rservice.selectMaxGuest(roomseq);
		  System.out.println("예약된방이드아아아아아====="+n);
		  if(Integer.parseInt(guest)>MaxGuest){
		  }
		  String nextPage = null;
          if(Integer.parseInt(guest)>MaxGuest) {
        	  session.setAttribute("MaxGuest", MaxGuest);
			  nextPage = "redirect:../historyback";
          }else if(n==1) {			  
			  nextPage = "redirect:../historyback";
		  }else if(n!=1&&Integer.parseInt(guest)<=MaxGuest) {
			 nextPage = "redirect:../RoomReserv";
		  }		 
			return nextPage;
	}

	@RequestMapping("/historyback")
	public String historyback() {
		return "historyback"; // historyback.jsp
	}

	@RequestMapping(value = "/loginCheck/KakaoPay")
	public String KakaoPay(HttpSession session, ResvDTO rdto, RedirectAttributes attr ,String seq, String hotelseq,
			String roomseq, int price) {
		System.out.println(price);
		attr.addFlashAttribute("seq", seq);
		attr.addFlashAttribute("hotelseq", hotelseq);
		attr.addFlashAttribute("roomseq", roomseq);
		session.setAttribute("price", price);
		return "redirect:../KakaoPay";
	}


	@RequestMapping("/loginCheck/paySuccess")
	public String paySuccess(HttpServletRequest request, String hotelseq,String u_id,
			String roomseq,String checkin,String checkout,int price,String guest) {
		  ResvDTO dto = new ResvDTO();
		  dto.setHotelseq(hotelseq);
		  dto.setU_id(u_id);
		  dto.setRoomseq(roomseq);
		  dto.setCheckin(checkin);
		  dto.setCheckout(checkout);
		  dto.setPrice(price);
		  dto.setGuest(guest);

		 System.out.println(dto);
		int n = service.resvInsert(dto);

		return "redirect:../loginCheck/resvMy";
	}

}
