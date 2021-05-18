package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.dto.ResvPageDTO;
import com.service.ResvService;

@Controller
public class ResvController {

	@Autowired
	ResvService service;

	@RequestMapping("/loginCheck/resvMy") // Interceptor
	public String resvMy(HttpSession session, HttpServletRequest request) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

			// 페이징
			String curPage = request.getParameter("curPage");// 현재 페이지 얻기
			if (curPage == null)
				curPage = "1";
			ResvPageDTO RpDTO = null;
			String u_id = dto.getU_id();
			System.out.println("회원 아이디 : " + u_id);
			System.out.println("curPage" + curPage);
			RpDTO = service.resvMyList(Integer.parseInt(curPage), u_id);
			// 페이징 끝
			System.out.println("ResvController : " + RpDTO);
			
			session.setAttribute("RpDTO", RpDTO);
			session.setAttribute("u_id", u_id);
			return "resvMy";
		
	}

	@RequestMapping("/loginCheck/resvCancel")
	public String resvCancel(@RequestParam("seq") int seq) {
		service.resvCancel(seq);
		return "redirect:../";
	}

}

