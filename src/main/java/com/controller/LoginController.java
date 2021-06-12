package com.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.kakao.KakaoUserInfo;
import com.service.MemberService;

@Controller
public class LoginController<kakao_restapi> {
	@Autowired
	MemberService service;
	//아이디 찾기 화면 전환
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
	
private com.kakao.kakao_restapi kakao_restapi = new com.kakao.kakao_restapi();
    
    @RequestMapping(value = "/oauth", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session) 
    {
    	 System.out.println("로그인 할때 임시 코드값");
         //카카오 홈페이지에서 받은 결과 코드
         System.out.println(code);
         System.out.println("로그인 후 결과값");
         //카카오 rest api 객체 선언
         com.kakao.kakao_restapi kr = new com.kakao.kakao_restapi();
         //결과값을 node에 담아줌
         JsonNode node = kr.getAccessToken(code);
         JsonNode accessToken = node.get("access_token");
         JsonNode userinfo = KakaoUserInfo.getKakaoUserInfo(accessToken);
         
         String id = userinfo.path("id").asText();
         String name = null;
         String email = null;
         
         JsonNode properties = userinfo.path("properties");
         JsonNode kakao_account = userinfo.path("kakao_account");

         name = properties.path("nickname").asText();
         email = kakao_account.path("email").asText();
         
         //결과값 출력
         System.out.println("node : " + node);
         //노드 안에 있는 access_token값을 꺼내 문자열로 변환
         if(accessToken != null)
         {
        	 String token = node.get("access_token").toString();
         }
         //세션에 담아준다.
        
         session.setAttribute("name", name);
         session.setAttribute("kemail", email);
         
         System.out.println("id : " + id);
         System.out.println("name : " + name);
         System.out.println("email : " + email);
         
         if(email != null)
         {
        	 session.setAttribute("mesg", "카카오 로그인이 완료되었습니다. 휴대전화 번호 입력을 위해 회원가입을 진행해주세요."); //사업자 등록하면 휴대폰 번호 가져올 수 있음 안되니 회원가입 진행
         }
         
         return "login_register";
    }
    
    @RequestMapping(value = "/kakaologout", produces = "application/json")
    public String kakaoLogout(HttpSession session) 
   {
        //kakao restapi 객체 선언
        com.kakao.kakao_restapi kr = new com.kakao.kakao_restapi();
        //노드에 로그아웃한 결과값음 담아줌 매개변수는 세션에 잇는 token을 가져와 문자열로 변환
        JsonNode node = kr.Logout(session.getAttribute("token").toString());
        //결과 값 출력
        System.out.println("로그인 후 반환되는 아이디 : " + node.get("id"));
        return "redirect:../";
    }    

}
