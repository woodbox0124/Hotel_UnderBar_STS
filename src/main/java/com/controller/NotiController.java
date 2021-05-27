package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotiController {

	//시작점
	@RequestMapping("/notification")
	public String noti_main() {
		return "notification";			
	}
	
	
}
