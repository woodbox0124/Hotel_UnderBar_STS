package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.ResvDTO;
import com.dto.RoomDTO;
import com.dto.RoomInfoDTO;
import com.dto.RoomPageDTO;
import com.service.RoomService;

@Controller
public class RoomController {
	@Autowired
	RoomService service;
	
	@RequestMapping(value = "/loginCheck/roomlist")
	public String RoomList(RoomDTO rDTO,String hotelname, String place, HttpServletRequest request, HttpSession session, RedirectAttributes attr)
	{	
		
		String curPage = request.getParameter("curPage");// 현재 페이지 얻기
		if (curPage == null)
			curPage = "1";
			RoomPageDTO RpDTO = null;
			String seq = rDTO.getSeq();
			RpDTO = service.roomListpage(Integer.parseInt(curPage), seq);

			attr.addFlashAttribute("RpDTO", RpDTO);
			attr.addFlashAttribute("hotelname", hotelname);
			attr.addFlashAttribute("place", place);
			attr.addFlashAttribute("seq", seq);
			return "redirect:../roomlist";
	}
		
	@RequestMapping("/loginCheck/roomdetail")
	   public String RoomDetail(String seq, String hotelname, RedirectAttributes attr)
	   {   
	      List<RoomInfoDTO> ilist = service.roomInfo(seq);
	      List<RoomDTO> rlist = service.roomList2(seq);
		
	      attr.addFlashAttribute("roominfo", ilist);
	      attr.addFlashAttribute("roomlist", rlist);
	      attr.addFlashAttribute("hotelname", hotelname);
	      
	      return "redirect:../roomdetail";
	   }
	
}
