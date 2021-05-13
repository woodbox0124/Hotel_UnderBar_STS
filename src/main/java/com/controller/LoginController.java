package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	//시작점
	@RequestMapping("/searchId")//리스트 무조건 뿌리기
	public String searchId() {
		System.out.println("searchId.jsp 불러옴");
		return "searchId";			
	}

}
