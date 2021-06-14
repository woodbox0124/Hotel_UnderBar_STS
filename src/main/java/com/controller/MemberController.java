package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.phone.Coolsms;
import com.service.BoardService;
import com.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService mService;
	@Autowired
	BoardService bService;
	//시작점
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	//휴대폰 인증
	@ResponseBody
	@RequestMapping(value = "/phone", method = RequestMethod.POST)
	  public void sendSms(HttpServletRequest request) throws Exception {

	    String api_key = "NCSCTTAH98GBNORK";
	    String api_secret = "JA6NOF4UH4U8XBWY4FIJ81BXNQIUO0WT";

	    Coolsms coolsms = new Coolsms(api_key, api_secret);
	    
	    HashMap<String, String> set = new HashMap<String, String>();

	    set.put("to", request.getParameter("to")); // 받는 사람
	    set.put("from", "01020700391"); // 발신번호
	    set.put("text", "안녕하세요. Hotel UnderBar입니다. 인증번호는 ["+request.getParameter("text")+"]입니다"); // 문자내용
	    set.put("type", "sms"); // 문자 타입

	    
	    JSONObject result = coolsms.send(set); // 보내기&전송결과받기

	    if ((boolean)result.get("status") == true) {
	      // 메시지 보내기 성공 및 전송결과 출력
	      System.out.println("인증이 완료되었습니다.");
	      System.out.println(result.get("group_id")); // 그룹아이디
	      System.out.println(result.get("result_code")); // 결과코드
	      System.out.println(result.get("result_message")); // 결과 메시지
	      System.out.println(result.get("success_count")); // 메시지아이디
	      System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
	    } else {
	      // 메시지 보내기 실패
	      System.out.println("실패");
	      System.out.println(result.get("code")); // REST API 에러코드
	      System.out.println(result.get("message")); // 에러메시지
	    }
	  }
	
	
	
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
		// 메일 제목, 내용
		String subject = "아이디 찾기에 성공 하였습니다.";
		String content = "고객님의 아이디는 "+u_id+"입니다.";		
		// 보내는 사람
		String from = "dltjrwhd3@naver.com";
		
		// 받는 사람
		String to = u_email;
		
		try {
			// 메일 내용 넣을 객체와, 이를 도와주는 Helper 객체 생성
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

			// 메일 내용을 채워줌
			mailHelper.setFrom(from);	// 보내는 사람 셋팅
			mailHelper.setTo(to);		// 받는 사람 셋팅
			mailHelper.setSubject(subject);	// 제목 셋팅
			mailHelper.setText(content);	// 내용 셋팅

			// 메일 전송
			mailSender.send(mail);
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
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
		String u_pw = "";
		for(int i = 0; i <12; i++)
		{
			u_pw += (char)((Math.random() * 26) + 97);
		}
		mdto.setU_pw(u_pw);
		mService.updatepw(u_id);
		mdto.setU_name(u_name);
		mdto.setU_phone(u_phone);
		mdto.setU_email(u_email);
		mdto.setU_id(u_id);
		System.out.println(u_name + "\t" + u_phone + "\t" + u_email + "\t" + u_id);
		xx.addFlashAttribute("mesg1", "메일을 확인해주세요.");
		// 메일 제목, 내용
				String subject = "임시 비밀번호 발급 안내입니다.";
				String content = "고객님의 임시 비밀번호는 "+u_pw+"입니다.";		
				// 보내는 사람
				String from = "dltjrwhd3@naver.com";
				
				// 받는 사람
				String to = u_email;
				
				try {
					// 메일 내용 넣을 객체와, 이를 도와주는 Helper 객체 생성
					MimeMessage mail = mailSender.createMimeMessage();
					MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

					// 메일 내용을 채워줌
					mailHelper.setFrom(from);	// 보내는 사람 셋팅
					mailHelper.setTo(to);		// 받는 사람 셋팅
					mailHelper.setSubject(subject);	// 제목 셋팅
					mailHelper.setText(content);	// 내용 셋팅

					// 메일 전송
					mailSender.send(mail);
					
				} catch(Exception e) {
					e.printStackTrace();
				}			
		System.out.println("searchPw 불러옴" + u_pw);
		return "redirect:/searchId";			
	}
	
	@RequestMapping(value = "/MemberAdd")
	public String MemberAdd(MemberDTO dto, HttpSession session) {
		String u_name = dto.getU_name();
		String u_phone = dto.getU_phone();
		String u_email = dto.getU_email();
		String u_id = dto.getU_id();
		String inputpass = dto.getU_pw();
		String u_pw = pwdEncoder.encode(inputpass);
		
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
			String inputpass = mdto.getU_pw();
			String u_pw = pwdEncoder.encode(inputpass);
			dto1.setU_pw(u_pw);
			dto1.setU_name(mdto.getU_name());
			dto1.setU_phone(mdto.getU_phone());
			dto1.setU_email(mdto.getU_email());		
			mService.memberUpdate(dto1);
			session.setAttribute("login", mdto);//다시 session에 저장
			session.setAttribute("mesg", "회원 정보 수정 완료");	
			nextPage = "redirect:../mypage";
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
	@RequestMapping(value = "/MemberIdCheck", method = RequestMethod.POST)
	public void MemberIdCheck(String u_id, HttpSession session, HttpServletResponse response) {
		System.out.println(u_id);
		int count = mService.idCheck(u_id);
		String mesg = "";
		if(count == 1)
		{
			mesg = "아이디가 중복됩니다. 다시 입력해주세요";
		}
		else
		{
			mesg = "아이디를 사용가능합니다. 계속 진행해주세요";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(mesg);
	}
}