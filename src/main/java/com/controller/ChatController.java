package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;

@Controller
public class ChatController {

		private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
		
	/*
	 * @RequestMapping(value="/login", method = RequestMethod.GET) public String
	 * login() { return "login"; }
	 * 
	 * @RequestMapping(value="/loginProcess", method = RequestMethod.POST) public
	 * String loginprocess(@RequestParam String id, HttpServletRequest request) {
	 * logger.info("Welcome "+id);
	 * 
	 * HttpSession session = request.getSession(); session.setAttribute("id", id);
	 * return "chat"; }
	 */
		
		@RequestMapping(value="/Chat", method = RequestMethod.GET)
		public String chat(HttpServletRequest request, HttpSession session1) {
			String id = "익명";
			if(session1.getAttribute("login") != null) {
			MemberDTO mdto = (MemberDTO)session1.getAttribute("login");
			id = mdto.getU_id();
			}
			logger.info("Welcome "+id);
			
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			return "chat";
		}
}
