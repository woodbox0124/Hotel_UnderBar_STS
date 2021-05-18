package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.PageDTO;
import com.service.HotelService;

@Controller
public class HotelController {
	
	@Autowired
HotelService service;
	
	@RequestMapping(value="/HotelSearch")
	public String HotelSearch(HttpServletRequest request,HttpSession session,@RequestParam("checkin") String checkin,@RequestParam("checkout") String checkout,
			@RequestParam("guest") String guest,@RequestParam("location") String location) {
		String curPage=null;
		if(curPage==null)curPage = "1";
		
		PageDTO pDTO = null;
		 if(location!="") { 
			  pDTO = service.hotelList1(Integer.parseInt(curPage), location); 
			  }else {
			  pDTO = service.hotelList1(Integer.parseInt(curPage));
			  } 
		 
		  request.setAttribute("pDTO", pDTO);
		  request.setAttribute("location", location);
		  System.out.println("pDTO"+pDTO);
		  System.out.println("location"+location);
		  
		  session.setAttribute("checkin",checkin); 
		  session.setAttribute("checkout", checkout);
		  session.setAttribute("location", location); 
		  session.setAttribute("guest",guest);
		return "hotelList";
	}
}
