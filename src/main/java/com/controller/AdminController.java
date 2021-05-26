package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	//관리자 페이지로 이동 합니다.
	@RequestMapping("/loginCheck/admin")
	public String admin() {
		return "redirect:../admin";
	}
	@RequestMapping("/loginCheck/adminMember")
	public String member() {
		return "redirect:../admin";
	}
	@RequestMapping("/loginCheck/adminHotel")
	public String hotel() {
		return "redirect:../admin";
	}
	@RequestMapping("/loginCheck/adminRoom")
	public String room() {
		return "redirect:../admin";
	}
	
	

}
