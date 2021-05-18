package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.ResvDTO;
import com.dto.RoomDTO;
import com.dto.RoomInfoDTO;
import com.service.RoomService;

@Controller
public class RoomController 
{
	@Autowired
	RoomService service;
	
	
	
	@RequestMapping(value = "/loginCheck/roomlist")
	public String RoomList(ResvDTO rDTO,String hotelname,String place, HttpSession session, RedirectAttributes attr)
	{	
		String seq = rDTO.getSeq();
		String checkin = rDTO.getCheckin();
		String checkout = rDTO.getCheckout();
		
		
		List<RoomDTO> list = service.roomList(seq);
		
		attr.addFlashAttribute("roomlist", list);
		attr.addFlashAttribute("hotelname", hotelname);
		attr.addFlashAttribute("place", place);
		attr.addFlashAttribute("checkin", checkin);
		attr.addFlashAttribute("checkout", checkout);
		
		System.out.println(list);
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
