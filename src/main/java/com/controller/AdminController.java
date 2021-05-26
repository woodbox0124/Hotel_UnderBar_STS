package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping("/loginCheck/admin")
	public String admin() {
		return "redirect:../admin";
	}

}
