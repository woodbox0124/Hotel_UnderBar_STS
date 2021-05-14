package com.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	//시작점
	@RequestMapping(value = "/MemberIdSearch")
	public String searchId(MemberDTO dto, RedirectAttributes xx) {
		String u_name = dto.getU_name();
		String u_phone = dto.getU_phone();
		String u_email = dto.getU_email();
		MemberDTO mdto = new MemberDTO();
		mdto.setU_name(u_name);
		mdto.setU_phone(u_phone);
		mdto.setU_email(u_email);
		System.out.println(u_name + "\t" + u_phone + "\t" + u_email);
		String u_id = service.idSearch(mdto);
		xx.addFlashAttribute("mesg1", "메일을 확인해주세요.");
		
		System.out.println("searchId 불러옴" + u_id);
		return "redirect:/searchId";			
	}
	
	@RequestMapping(value = "/MemberPwSearch")
	public String searchPw(MemberDTO dto, RedirectAttributes xx) {
		String u_name = dto.getU_name();
		String u_phone = dto.getU_phone();
		String u_email = dto.getU_email();
		String u_id = dto.getU_id();
		
		MemberDTO mdto = new MemberDTO();
		mdto.setU_name(u_name);
		mdto.setU_phone(u_phone);
		mdto.setU_email(u_email);
		mdto.setU_id(u_id);
		System.out.println(u_name + "\t" + u_phone + "\t" + u_email + "\t" + u_id);
		String u_pw = service.pwSearch(mdto);
		xx.addFlashAttribute("mesg1", "메일을 확인해주세요.");
		
		System.out.println("searchPw 불러옴" + u_pw);
		return "redirect:/searchId";			
	}
	
	@RequestMapping(value = "/MemberAdd")
	public String MemberAdd(MemberDTO dto, HttpSession session) {
		String u_name = dto.getU_name();
		String u_phone = dto.getU_phone();
		String u_email = dto.getU_email();
		String u_id = dto.getU_id();
		String u_pw = dto.getU_pw();
		
		MemberDTO mdto = new MemberDTO();
		
		mdto.setU_name(u_name);
		mdto.setU_phone(u_phone);
		mdto.setU_email(u_email);
		mdto.setU_id(u_id);
		mdto.setU_pw(u_pw);
		
		int n = service.MemberAdd(mdto);
		System.out.println(u_name + "\t" + u_phone + "\t" + u_email + "\t" + u_id + "\t" + u_pw);
		System.out.println("회원가입 성공" + n);
		if(n==1){		
			session.setAttribute("login", mdto);
			session.setAttribute("mesg", "회원가입 성공");
			}else {
		    session.setAttribute("mesg", "비정상적인 접근 입니다.");
			}							
		return "main";			
	}
	

}