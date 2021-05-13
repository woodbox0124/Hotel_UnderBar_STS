package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	//시작점
	@RequestMapping("/")//리스트 무조건 뿌리기
	public ModelAndView goodsList() {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("main"); //main.jsp=
		return mav;			
	}
	
	@RequestMapping("/login_register")//리스트 무조건 뿌리기
	public String login_register() {
		System.out.println("login");
		return "login_register";			
	}

}
