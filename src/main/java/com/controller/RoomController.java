package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		/*
		 * String checkin = rDTO.getCheckin(); String checkout = rDTO.getCheckout();
		 */
		
		
		List<RoomDTO> list = service.roomList(seq);
		
		attr.addFlashAttribute("roomlist", list);
		attr.addFlashAttribute("hotelname", hotelname);
		attr.addFlashAttribute("place", place);
		/*
		 * attr.addFlashAttribute("checkin", checkin);
		 * attr.addFlashAttribute("checkout", checkout);
		 */
		
		System.out.println("방 정보"+list);
		return "redirect:../roomlist";
	}
	
	
	@RequestMapping("/loginCheck/roomdetail")
	   public String RoomDetail(String seq, String hotelname,
	          String checkin, String checkout, String place, RedirectAttributes attr)
	   {   
	      List<RoomInfoDTO> ilist = service.roomInfo(seq);
	      List<RoomDTO> rlist = service.roomList2(seq);
		
	      attr.addFlashAttribute("roominfo", ilist);
	      attr.addFlashAttribute("roomlist", rlist);
	      attr.addFlashAttribute("hotelname", hotelname);

	      
	      return "redirect:../roomdetail";
	   }
}
