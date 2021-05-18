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
public class BoardController {

	@Autowired
	MemberService mService;
	@Autowired
	BoardService bService;
	//시작점
	
	//board List
	@RequestMapping("/loginCheck/boardList")
	public String boardList(@RequestParam(required=false, defaultValue="1") String curPage ,
			HttpSession session, @RequestParam(required=false, defaultValue="title") String searchName,
			@RequestParam(required=false, defaultValue="") String searchValue) throws Exception {
		System.out.println(curPage);
		System.out.println(searchName);
		System.out.println(searchValue);
	
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);		
		System.out.println(map);
		BoardPageDTO pDTO= bService.boardList(Integer.parseInt(curPage),map);	
		System.out.println(pDTO);
		session.setAttribute("pDTO", pDTO);
		
		return "redirect:../boardList";
	}
	//board write
	@RequestMapping("/loginCheck/boardWrite")
	public String boardList(HttpSession session) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String u_id = dto.getU_id();
		MemberDTO mdto = mService.myPage(u_id);
		System.out.println(mdto);
		session.setAttribute("login", mdto);//다시 session에 저장
		return "redirect:../boardWrite";
	}
	
	//board write Insert
	@RequestMapping(value="/loginCheck/boardInsert")
	public String boardInsert(BoardDTO bDTO, HttpSession session) {
		System.out.println(bDTO);
		bDTO.setAuthor(bDTO.getAuthor());
		bDTO.setTitle(bDTO.getTitle());
		bDTO.setContent(bDTO.getContent());
		
		int n = bService.boardInsert(bDTO);
		System.out.println(n);
		return "redirect:../loginCheck/boardList";
	}
}