package com.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	MemberService service;
	//시작점
	@RequestMapping("/searchId")//리스트 무조건 뿌리기
	public String searchId() {
		System.out.println("searchId.jsp 불러옴");
		return "searchId";			
	}
	//로그인 
	@RequestMapping("/login")
	public String login(MemberDTO mdto, HttpSession session, 
			@CookieValue(value="savepw", required = false) Cookie savepw,
			HttpServletResponse response) {
		String u_id = mdto.getU_id();
		String u_pw = mdto.getU_pw();
		System.out.println(u_id+"\t"+u_pw);
		//1.id, pass 이용 map 생성 // userid passwd 키
		HashMap<String, String> map = new HashMap<>();
		map.put("u_id", u_id);
		map.put("u_pw", u_pw);
		MemberDTO dto = service.login(map);//login 인증	
		System.out.println(dto);
		String nextPage=null;//이동페이지 저장
		if(dto!=null) {//회원인 경우
		    nextPage="main"; // MainServlet 요청 데이터 가져와서 출력
		    session.setAttribute("login", dto);	//로그인 정보 저장	
		    }else{ //dto==null 회원이 아닌 경우	
		    	int num  = service.idCheck(u_id);//아이디가 일치하지 않을 경우
		    	int num1 = service.pwchk(u_pw);// 비밀번호가 일치하지 않을 경우
		    	
		    	System.out.println("num \t"+num);
				System.out.println("num1 \t"+num1);
				if(num==1 || num1!=0){
					session.setAttribute("mesg", "아이디 및 비밀번호를 확인 하시길 바랍니다.");
				}else if(num==0 && num1==0) {
						session.setAttribute("mesg", "회원가입 후 이용 바랍니다.");						
					}
		           nextPage="login_register";				
	     }//end else				
		return nextPage ;
	}
	@RequestMapping("loginCheck/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("login");
		session.setAttribute("mesg", "로그아웃이 완료 되었습니다.");
		return "redirect:../";
	}

}
