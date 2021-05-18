package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.MemberDTO;
import com.dto.ResvMyDTO;
import com.service.ResvService;

@Controller
public class ResvController {

	@Autowired
	ResvService service;

	@RequestMapping("/loginCheck/resvMy") // Interceptor
	public String myPage(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

		String u_id = dto.getU_id();
		
		System.out.println("회원 아이디  : " + u_id);
		
		List<ResvMyDTO> list = service.resvMy(u_id);
		for (ResvMyDTO r : list) {
			System.out.println("예약 내역 : " + r);
		}
		/*
		 * if (curPage == null) curPage = "1"; ResvPageDTO RpDTO = null; ResvService
		 * service = new ResvService(); String u_id = dto.getU_id();
		 * System.out.println("회원 아이디  : " + u_id); RpDTO =
		 * service.resvMyList(Integer.parseInt(curPage), u_id);
		 * System.out.println("TEST : " + RpDTO); session.setAttribute("RpDTO", RpDTO);
		 */
		session.setAttribute("list", list);
		return "redirect:../resvMy";

	}
	/*
	 * @RequestMapping(value = "/loginCheck/resvMy") // Interceptor public String
	 * myPage(@RequestParam(required=false, defaultValue="1") String curPage,
	 * HttpSession session) { MemberDTO dto = (MemberDTO)
	 * session.getAttribute("login");
	 * 
	 * if (curPage == null) curPage = "1"; ResvPageDTO RpDTO = null; ResvService
	 * service = new ResvService(); String u_id = dto.getU_id();
	 * System.out.println("회원 아이디  : " + u_id); RpDTO =
	 * service.resvMyList(Integer.parseInt(curPage), u_id);
	 * System.out.println("TEST : " + RpDTO); session.setAttribute("RpDTO", RpDTO);
	 * return "redirect:../resvMy";
	 * 
	 * }
	 */
}
