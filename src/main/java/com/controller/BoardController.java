package com.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String boardList() {
		
		return "redirect:../boardWrite";
	}
	
	//board write Insert
	@RequestMapping(value="/loginCheck/boardInsert", produces="text/plain;charset=UTF-8")
	public String boardInsert(BoardDTO bDTO, HttpSession session) {
		System.out.println(bDTO);
		bDTO.setAuthor(bDTO.getAuthor());
		bDTO.setTitle(bDTO.getTitle());
		bDTO.setContent(bDTO.getContent());
		
		int n = bService.boardInsert(bDTO);
		System.out.println(n);
		return "redirect:../loginCheck/boardList";
	}
	//board write 불러오기 
	@RequestMapping(value="/loginCheck/boardRetrive", produces="text/plain;charset=UTF-8")
	public ModelAndView boardRetrieve(@RequestParam int num, HttpSession session) {
		
		BoardDTO bDTO = bService.selectByNum(num);
		System.out.println(bDTO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("bDTO", bDTO);
		mav.setViewName("boardretrieve");
		
		
		return mav;
	}
}