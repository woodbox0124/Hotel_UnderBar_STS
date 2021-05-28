package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.FaqDTO;
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
	
	//글 작성 
	@RequestMapping("/loginCheck/notiInsert")
	public String noti_insert(NotiDTO dto) {
		nService.notiInsert(dto);
		return "redirect:../notification";			
	}
	//글 수정  
	@RequestMapping("/loginCheck/notiUpdate")
	public String noti_update(NotiDTO dto) {
		NotiDTO nDTO = new NotiDTO();
		System.out.println(dto.getSubject());
		nDTO.setNum(dto.getNum());
		nDTO.setSubject(dto.getSubject());
		nDTO.setAuthor(dto.getAuthor());
		nDTO.setContent(dto.getContent());
		System.out.println("update nDTO : "+nDTO);
		nService.notiUpdate(nDTO);
		return "redirect:../notification";			
	}
	//글 삭제
	@RequestMapping("/loginCheck/notiDelete")
	public String noti_delete(@RequestParam int num,RedirectAttributes attr) {
		nService.notiDelete(num);
		return "redirect:../notification";			
	}
	
	//글보기 
	@RequestMapping("/notiCheck")
	public String noti_retrieve(HttpServletRequest request,@RequestParam int num) {
		nService.updateHit(num);
		NotiDTO nDTO = nService.notiRetrieve(num);
		request.setAttribute("nDTO",nDTO);
		return "notiRetrieve";
	}
	
	//FAQ글쓰기 클릭 
	@RequestMapping("/loginCheck/faqWrite")
	public String faq_write() {
		return "redirect:../faqWrite";
	}
	
	//FAQ글쓰기 
	@RequestMapping("/loginCheck/faqInsert")
	public String faq_insert(FaqDTO dto) {
		nService.faqInsert(dto);
		return "redirect:../notification";
	}
	
	
	
}
