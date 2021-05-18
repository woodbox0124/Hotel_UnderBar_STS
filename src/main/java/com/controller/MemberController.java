package com.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.BoardDTO;
import com.dto.BoardPageDTO;
import com.dto.MemberDTO;
import com.service.BoardService;
import com.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService mService;
	@Autowired
	BoardService bService;
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
		String u_id = mService.idSearch(mdto);
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
		String u_pw = mService.pwSearch(mdto);
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
		
		int n = mService.MemberAdd(mdto);
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
	@RequestMapping("/loginCheck/myPage") //Interceptor
	public String myPage(HttpSession session) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");     
		// 사용자 id를 꺼내 sevice.mypage(id)이용 DB에서 데이터를 다시 가져오기	
		String u_id = dto.getU_id();
		MemberDTO mdto = mService.myPage(u_id);
		System.out.println(mdto);
		session.setAttribute("login", mdto);//다시 session에 저장
		return "redirect:../mypage";
	}
	@RequestMapping("/loginCheck/MemberUpdate")
	public String MemberUpdate(MemberDTO mdto , HttpSession session) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String nextPage = null; //이동할 페이지		
		// 사용자 id를 꺼내 sevice.mypage(id)이용 DB에서 데이터를 다시 가져오기
		if(dto!=null) {//로그인 정보가 있는 경우					
			MemberDTO dto1 = new MemberDTO();
			dto1.setU_id(mdto.getU_id());
			dto1.setU_pw(mdto.getU_pw());
			dto1.setU_name(mdto.getU_name());
			dto1.setU_phone(mdto.getU_phone());
			dto1.setU_email(mdto.getU_email());		
			System.out.println(dto1);
			mService.memberUpdate(dto1);
			System.out.println(mdto);
			session.setAttribute("login", mdto);//다시 session에 저장
			session.setAttribute("mesg", "회원 정보 수정 완료");	
			nextPage = "mypage";
		}else {//로그인이 안된 경우
			nextPage = "login_register";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");			
		}
		return nextPage ;
	}
	
	@RequestMapping("/loginCheck/MemberDelete")
	public String MemberDelete(String u_id, HttpSession session) {
		System.out.println(u_id);
		mService.MemberDelete(u_id);
		session.removeAttribute("login");	
		session.setAttribute("mesg", "회원 탈퇴 완료");	
		return "redirect:../";
	}
}