package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.dto.ResvDTO;
import com.dto.ResvPageDTO;
import com.service.ResvService;

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
	public String KakaoPay(HttpServletRequest request, HttpSession session) {
		String hotelseq = request.getParameter("hotelseq");
		String u_id = request.getParameter("u_id");
		String roomseq = request.getParameter("roomseq");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		int price = Integer.parseInt(request.getParameter("price"));
		String guest = request.getParameter("guest");
		String u_phone = request.getParameter("u_phone");
		System.out.println(price);
		session.setAttribute("hotelseq", hotelseq);
		session.setAttribute("u_id", u_id);
		session.setAttribute("roomseq", roomseq);
		session.setAttribute("checkin", checkin);
		session.setAttribute("checkout", checkout);
		session.setAttribute("price", price);
		session.setAttribute("guest", guest);
		session.setAttribute("u_phone", u_phone);
		/*
		 * model.addAttribute(hotelseq); model.addAttribute(u_id);
		 * model.addAttribute(roomseq); model.addAttribute(checkin);
		 * model.addAttribute(checkout); model.addAttribute(price);
		 * model.addAttribute(guest); model.addAttribute(u_phone);
		 */

		return "redirect:../KakaoPay";
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
	public String PayFail(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
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
