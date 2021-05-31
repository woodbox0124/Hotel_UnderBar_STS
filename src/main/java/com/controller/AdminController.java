package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.AdminMemberPageDTO;
import com.dto.BoardPageDTO;
import com.dto.MemberDTO;
import com.service.AdminService;
import com.service.MemberService;

@Controller
public class AdminController {
	@Autowired
	AdminService service;
	@Autowired
	MemberService mService;
	
	//회원관리 페이지로 이동 합니다.
	@RequestMapping("/loginCheck/adminMember")
	public String member(@RequestParam(required=false, defaultValue="1") String curPage ,
			 @RequestParam(required=false, defaultValue="id") String searchName,
				@RequestParam(required=false, defaultValue="") String searchValue, RedirectAttributes attr)throws Exception {
		System.out.println(curPage);
		System.out.println(searchName);
		System.out.println(searchValue);
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);		
		System.out.println(map);
		AdminMemberPageDTO ampDTO= service.adminMember(Integer.parseInt(curPage),map);	
		System.out.println("Controller"+ampDTO);
		attr.addFlashAttribute("ampDTO",ampDTO);
		return "redirect:../adminMember";
	}
	//호텔관리 페이지로 이동 합니다.
	@RequestMapping("/loginCheck/adminHotel")
	public String hotel() {
		return "redirect:../adminHotel";
	}
	//호텔방관리 페이지로 이동 합니다.
	@RequestMapping("/loginCheck/adminRoom")
	public String room() {
		return "redirect:../adminRoom";
	}
	@RequestMapping("/loginCheck/searchName")
	public String searchName(@RequestParam("searchName") String searchName, 
			@RequestParam("searchValue")String searchValue,RedirectAttributes attr) {	
		System.out.println(searchName);
		System.out.println(searchValue);
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);		
		List<MemberDTO> list = service.serachName(map);
		System.out.println(list);
		attr.addFlashAttribute("member", list);		
		return "redirect:../adminMember";
	}
	@RequestMapping("/loginCheck/delete")
	public @ResponseBody void delete(@RequestParam("u_id") String u_id) {
		System.out.println(u_id);
		mService.MemberDelete(u_id);
	}
	@RequestMapping("/loginCheck/update")
	public String update(@RequestParam("u_id") String u_id, RedirectAttributes att) {
		System.out.println(u_id);
		MemberDTO mdto = mService.myPage(u_id);
		System.out.println(mdto);
		att.addFlashAttribute("mdto", mdto);
		return "redirect:../admin/update";
	}
	
	@RequestMapping("/loginCheck/AdminMemberUpdate")
	public @ResponseBody void AdminMemberUpdate(MemberDTO mdto) {			
			MemberDTO dto1 = new MemberDTO();
			dto1.setU_id(mdto.getU_id());
			dto1.setU_pw(mdto.getU_pw());
			dto1.setU_name(mdto.getU_name());
			dto1.setU_phone(mdto.getU_phone());
			dto1.setU_email(mdto.getU_email());		
			System.out.println(dto1);
			mService.memberUpdate(dto1);
			System.out.println("수정완료    "+mdto);
	}

}
