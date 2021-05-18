package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.dto.RoomDTO;
import com.dto.RoomInfoDTO;
import com.service.RoomService;

import sun.print.resources.serviceui;

@Controller
public class RoomController 
{
	@Autowired
	RoomService service;
	
	@RequestMapping(value = "/loginCheck/roomlist")
	public String RoomList(HttpServletRequest request, HttpSession session, Model model
			,@RequestParam("seq") String seq, @RequestParam("checkin") String checkin
			,@RequestParam("checkout") String checkout,@RequestParam("hotelname") String hotelname
			,@RequestParam("place") String place)
	{
		
		System.out.println(seq + "\t" + hotelname + "\t" + checkin + "\t" + checkout+ "\t" + place);
		MemberDTO mdto = (MemberDTO)session.getAttribute("login");
		
		List<RoomDTO> list = service.roomList(seq);
		
		session.setAttribute("roomlist", list);
		System.out.println(list);
		
		 session.setAttribute("hotelname", hotelname);
		 session.setAttribute("checkin",checkin); 
		 session.setAttribute("checkout", checkout);
		 session.setAttribute("place", place);
		return "redirect:../roomlist";
	}
	
	@RequestMapping(value = "/loginCheck/RoomReserv")
	public String KakaoPay(HttpServletRequest request, Model model, HttpServletResponse response
			,@RequestParam("hotelseq") String hotelseq, @RequestParam("checkin") String checkin
			,@RequestParam("checkout") String checkout,@RequestParam("hotelname") String hotelname
			,@RequestParam("guest") String guest, @RequestParam("roomseq") String roomseq
			,@RequestParam("price") int price, @RequestParam("u_id") String u_id 
			,@RequestParam("location") String location, @RequestParam("name") String name) throws IOException {
		
		model.addAttribute(hotelseq);
		model.addAttribute(u_id);
		model.addAttribute(roomseq);
		model.addAttribute(checkin);
		model.addAttribute(checkout);
		model.addAttribute(price);
		model.addAttribute(guest);
		model.addAttribute(hotelname);
		model.addAttribute(location);
		model.addAttribute(name);
		
		RoomService service = new RoomService();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("roomseq", roomseq);
		map.put("checkin", checkin);
		int n = service.date(map);
		
		if(n==1) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
				out.print("<html><head>");
				out.print("<meta http-equiv=\'Content-Type\' content=\'text/html; charset=utf-8\'>");
				out.println("<script language='javascript'>");
		        out.println("alert('이미 예약이 된 방입니다.');");
		        out.println("history.back();");
		        out.println("</script>");
		        out.print("</head></html>");
		}
			return "/redirect:../RoomReserv";
	}
	@RequestMapping(value = "/loginForm/roomdetail")
	public String RoomDetail(HttpServletRequest request, HttpSession session, @RequestParam("seq") String seq)
	{	
		List<RoomInfoDTO> ilist = service.roomInfo(seq);
		List<RoomDTO> rlist = service.roomList2(seq);
		
		request.setAttribute("roominfo", ilist);
		request.setAttribute("roomlist", rlist);
		
		return "/roomdetail";
	}
}
