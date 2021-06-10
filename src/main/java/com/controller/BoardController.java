package com.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.BoardDTO;
import com.dto.BoardPageDTO;
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
	public String boardList(RedirectAttributes attr,@RequestParam(required=false, defaultValue="1") String curPage ,
		 @RequestParam(required=false, defaultValue="title") String searchName,
		@RequestParam(required=false, defaultValue="") String searchValue) throws Exception {
		System.out.println(curPage);
		System.out.println(searchName);
		System.out.println(searchValue);
	
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);		
		System.out.println(map);
		BoardPageDTO pDTO= bService.boardList(Integer.parseInt(curPage),map);	
		System.out.println("Controller"+pDTO);
		attr.addFlashAttribute("pDTO",pDTO);
		return "redirect:../boardList";
	}
	//board write
	@RequestMapping(value="loginCheck/boardWrite",method= {RequestMethod.GET, RequestMethod.POST})
	public String boardList(HttpSession session) {
		session.getAttribute("login");
		return "redirect:../boardWrite";
	}
	
	//board write Insert
	@RequestMapping(value="/loginCheck/boardInsert", produces="text/plain;charset=UTF-8")
	public String boardInsert(BoardDTO bDTO,RedirectAttributes attr) {
		System.out.println(bDTO);
		bDTO.setAuthor(bDTO.getAuthor());
		bDTO.setTitle(bDTO.getTitle());
		bDTO.setContent(bDTO.getContent());
		
		int n = bService.boardInsert(bDTO);
		System.out.println(bDTO);
		attr.addFlashAttribute("bDTO",bDTO);
		System.out.println(n);
		return "redirect:../loginCheck/boardList";
	}
	//board write 불러오기 
	@RequestMapping(value="boardRetrieve", produces="text/plain;charset=UTF-8")
	public ModelAndView boardRetrieve(@RequestParam int num) {
		System.out.println(num);
		//조회수 증가
		bService.readCnt(num);
		BoardDTO bDTO = bService.selectByNum(num);
		System.out.println(bDTO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("bDTO", bDTO);
		mav.setViewName("boardretrieve");
		return mav;
	}
	//board 답글쓰기
	@RequestMapping(value="/loginCheck/boardUpdate", produces="text/plain;charset=UTF-8")
	public String boardUpdate(BoardDTO bDTO) {
		System.out.println(bDTO+"BOARDUPDATE");
		//조회수 증가
		int num = bDTO.getNum();
		bService.readCnt(num);
		bService.boardUpdate(bDTO);
		
		return "redirect:../loginCheck/boardList";
	}
	@RequestMapping(value="/loginCheck/boardDelete", produces="text/plain;charset=UTF-8")
	public String boardDelete(BoardDTO bDTO) {
		System.out.println(bDTO);
		int num = bDTO.getNum();
		System.out.println(num);
		bService.boardDelete(num);
		
		return "redirect:../loginCheck/boardList";
	}
	@RequestMapping(value="/loginCheck/boardAnswer", produces="text/plain;charset=UTF-8")
	public ModelAndView boardAnswer(BoardDTO bDTO) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("bDTO",bDTO);
		mav.setViewName("boardAnswer");
		System.out.println("answer : "+bDTO);
		return mav;
	}
	@RequestMapping(value="/loginCheck/boardAnsInsert", produces="text/plain;charset=UTF-8")
	public String boardAnsInsert(BoardDTO bDTO,RedirectAttributes attr) {
		System.out.println(bDTO);
		bDTO.setAuthor(bDTO.getAuthor());
		bDTO.setTitle(bDTO.getTitle());
		bDTO.setContent(bDTO.getContent());
		
		
		int n = bService.boardAnsInsert(bDTO);
		System.out.println(bDTO);
		attr.addFlashAttribute("bDTO",bDTO);
		System.out.println(n);
		return "redirect:../loginCheck/boardList";
	}
}