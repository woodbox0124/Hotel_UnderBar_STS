package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
	public String myPage(HttpSession session, HttpServletRequest request,RedirectAttributes attr) {
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

		attr.addFlashAttribute("RpDTO", RpDTO);
		attr.addFlashAttribute("u_id", u_id);
		return "redirect:../resvMy";


	}
	 //예약 취소
	@RequestMapping("/loginCheck/resvCancel")
	public String resvCancel(@RequestParam("seq") int seq) {
		service.resvCancel(seq);
		return "redirect:../resvMy";
	}
	
	@RequestMapping(value = "/loginCheck/RoomReserv")
	public String RoomReserv(ResvDTO rdto, String hotelname, String roomseq, int price, 
		String hotelseq , HttpSession session, RedirectAttributes attr, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		/*
		 * attr.addFlashAttribute("checkin", checkin);
		 * attr.addFlashAttribute("checkout", checkout); attr.addFlashAttribute("guest",
		 * guest);
		 */
		attr.addFlashAttribute("hotelseq", hotelseq);
		attr.addFlashAttribute("roomseq", roomseq);
		//attr.addFlashAttribute("u_id", u_id);
		/*
		 * attr.addFlashAttribute("name", name); attr.addFlashAttribute("location",
		 * location);
		 */
		attr.addFlashAttribute("hotelname", hotelname);
		attr.addFlashAttribute("price", price);
		System.out.println(price);
		
		/*
		 * RoomService rservice = new RoomService(); HashMap<String, String> map = new
		 * HashMap<String, String>(); map.put("roomseq", roomseq);
		 * map.put("checkin",checkin); int n = rservice.date(map);
		 * System.out.println("n : " + n); if(n==1) {
		 * request.setCharacterEncoding("UTF-8");
		 * response.setContentType("text/html; charset=utf-8"); PrintWriter out =
		 * response.getWriter(); out.print("<html><head>"); out.
		 * print("<meta http-equiv=\'Content-Type\' content=\'text/html; charset=utf-8\'>"
		 * ); out.println("<script language='javascript'>");
		 * out.println("alert('이미 예약이 된 방입니다.');"); out.println("history.back();");
		 * out.println("</script>"); out.print("</head></html>"); }
		 */
		 
			return "redirect:../RoomReserv";
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
	
	/*
	 * @RequestMapping(value = "/payFail") public String PayFail(HttpServletRequest
	 * request, HttpServletResponse response, HttpSession session, String checkin,
	 * String checkout, String guest, String location) throws ServletException,
	 * IOException {
	 * 
	 * session.setAttribute("checkin", checkin); session.setAttribute("checkout",
	 * checkout); session.setAttribute("guest", guest);
	 * session.setAttribute("location", location);
	 * 
	 * return "hotelList"; }
	 */
}
