package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
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

	//예약 내역
	@RequestMapping("/loginCheck/resvMy") // Interceptor
	public String myPage(HttpSession session, HttpServletRequest request) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

		// 페이징
		String curPage = request.getParameter("curPage");// 현재 페이지 얻기
		if (curPage == null)
			curPage = "1";
		ResvPageDTO RpDTO = null;
		String u_id = dto.getU_id();
		System.out.println("회원 아이디 : " + u_id);
		System.out.println("curPage" + curPage);
		RpDTO = service.resvMyList(Integer.parseInt(curPage), u_id);
		// 페이징 끝
		System.out.println("ResvController : " + RpDTO);

		session.setAttribute("RpDTO", RpDTO);
		session.setAttribute("u_id", u_id);
		return "resvMy";

	}
	 //예약 취소
	@RequestMapping("/loginCheck/resvCancel")
	public String resvCancel(@RequestParam("seq") int seq) {
		service.resvCancel(seq);
		return "redirect:../";
	}

	@RequestMapping(value = "/loginCheck/KakaoPay")
	public String KakaoPay(HttpServletRequest request, ResvDTO rdto, String seq) {
		
		/*
		 * System.out.println(list); String hotelseq = request.getParameter("hotelseq");
		 * String u_id = request.getParameter("u_id"); String roomseq =
		 * request.getParameter("roomseq"); String checkin =
		 * request.getParameter("checkin"); String checkout =
		 * request.getParameter("checkout"); int price =
		 * Integer.parseInt(request.getParameter("price")); String guest =
		 * request.getParameter("guest"); String u_phone =
		 * request.getParameter("u_phone");
		 * 
		 * ModelAndView mav = new ModelAndView();
		 * 
		 * mav.addObject("hotelseq", hotelseq); mav.addObject("u_id", u_id);
		 * mav.addObject("roomseq", roomseq); mav.addObject("checkin", checkin);
		 * mav.addObject("checkout", checkout); mav.addObject("price", price);
		 * mav.addObject("guest", guest); mav.addObject("u_phone", u_phone);
		 * mav.setViewName("redirect:../KakaoPay");
		 */
		
		return "redirect:../KakaoPay";
	}
	
	@RequestMapping(value = "/loginCheck/RoomReserv")
	public String RoomReserv(ResvDTO rdto, String location, String hotelname, String roomseq, int price, 
		String checkin, HttpSession session, RedirectAttributes attr, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		
		System.out.println("roomseq :" + roomseq);
		List<ResvDTO> list = service.payList(roomseq);
		System.out.println(list);
		attr.addFlashAttribute("paylist", list);
		attr.addFlashAttribute("location", location);
		attr.addFlashAttribute("hotelname", hotelname);
		session.setAttribute("price", price);
		System.out.println(price);
		
		  RoomService rservice = new RoomService();
		  HashMap<String, String> map = new HashMap<String, String>(); 
		  map.put("roomseq", roomseq); 
		  map.put("checkin",checkin); 
		  int n = rservice.date(map);
		  System.out.println("n : " + n);
		  if(n==1) { 
		  request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html; charset=utf-8"); 
		  PrintWriter out = response.getWriter(); 
		  out.print("<html><head>"); 
		  out.print("<meta http-equiv=\'Content-Type\' content=\'text/html; charset=utf-8\'>"); out.println("<script language='javascript'>");
		  out.println("alert('이미 예약이 된 방입니다.');"); 
		  out.println("history.back();");
		  out.println("</script>"); 
		  out.print("</head></html>"); 
		  }
		 
			return "redirect:../RoomReserv";
	}
	
	@RequestMapping(value = "/loginCheck/paySuccess")
	public String paySuccess(HttpServletRequest request) {
		String hotelseq = request.getParameter("hotelseq");
		String u_id = request.getParameter("u_id");
		String roomseq = request.getParameter("roomseq");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		int price = Integer.parseInt(request.getParameter("price"));
		String guest = request.getParameter("guest");
		
		ResvDTO dto = new ResvDTO();
		dto.setHotelseq(hotelseq);
		dto.setU_id(u_id);
		dto.setRoomseq(roomseq);
		dto.setCheckin(checkin);
		dto.setCheckout(checkout);
		dto.setPrice(price);
		dto.setGuest(guest);
		System.out.println(dto);
		ResvService service = new ResvService();
		int n = service.resvInsert(dto);
		System.out.println(n);
		
		return "redirect:../resvMy.jsp";
	}
	
	@RequestMapping(value = "/loginCheck/PayFail")
	public String PayFail(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		String guest = request.getParameter("guest");
		String location = request.getParameter("location");
		
		session.setAttribute("checkin", checkin);
		session.setAttribute("checkout", checkout);
		session.setAttribute("guest", guest);
		session.setAttribute("location", location);
		
		
		return "redirect:../HotelSearch";
	}
}
