package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.NotiDTO;
import com.dto.NotiPageDTO;
import com.service.NotiService;

@Controller
public class NotiController {
	@Autowired
	NotiService nService;
	//시작점
	@RequestMapping("/notification")
	public ModelAndView noti_main(@RequestParam(required=false, defaultValue="1") String curPage) {
		System.out.println("notification curpage" + curPage);
		NotiPageDTO pDTO= nService.notiList(Integer.parseInt(curPage));	
		ModelAndView mav = new ModelAndView();
		mav.addObject("pDTO",pDTO);
		mav.setViewName("notification");
		System.out.println("notiPDTO : "+pDTO.toString());
		return mav;			
	}
	
	//글쓰기 클릭 
	@RequestMapping("/loginCheck/notiWrite")
	public String noti_write() {
		return "redirect:../notiWrite";			
	}
	
	//글작성 
	@RequestMapping("/loginCheck/notiInsert")
	public String noti_insert(NotiDTO dto) {
		nService.notiInsert(dto);
		return "redirect:../notification";			
	}
	
	@RequestMapping("/notiCheck")
	public String noti_retrieve(HttpServletRequest request,@RequestParam int num) {
		System.out.println(num);
		NotiDTO nDTO = nService.notiRetrieve(num);
		request.setAttribute("nDTO",nDTO);
		return "notiRetrieve";
	}
	
	
	
}
