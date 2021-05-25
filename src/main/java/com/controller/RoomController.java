package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
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
	
	
	@RequestMapping("/loginCheck/roomdetail")
	   public String RoomDetail(HttpSession session, @RequestParam("seq") String seq, @RequestParam(value = "hotelname", required=false) String hotelname,
	         @RequestParam(value = "checkin", required=false) String checkin, @RequestParam(value = "checkout", required=false) String checkout, 
	         @RequestParam(value = "place", required=false) String place, RedirectAttributes attr)
	   {   
	      MemberDTO mdto = (MemberDTO)session.getAttribute("login");
	      List<RoomInfoDTO> ilist = service.roomInfo(seq);
	      List<RoomDTO> rlist = service.roomList2(seq);
	      
	      attr.addAttribute("login", mdto);
	      attr.addAttribute("roominfo", ilist);
	      attr.addAttribute("roomlist", rlist);
	      attr.addAttribute("hotelname", hotelname);
	      attr.addAttribute("checkin", checkin);
	      attr.addAttribute("checkout", checkout);
	      attr.addAttribute("place", place);
	      
	      return "/roomdetail";
	   }
}
