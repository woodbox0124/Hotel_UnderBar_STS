package com.controller;

import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.AdminMemberPageDTO;

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
				@RequestParam(required=false, defaultValue="") String searchValue, HttpSession session)throws Exception {
		System.out.println(curPage);
		System.out.println(searchName);
		System.out.println(searchValue);
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);		
		System.out.println(map);
		AdminMemberPageDTO ampDTO= service.adminMember(Integer.parseInt(curPage),map);	
		System.out.println("Controller"+ampDTO);
		session.setAttribute("ampDTO",ampDTO);
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
	
	@RequestMapping(value = "/loginCheck/AdminMemberUpdate", method = RequestMethod.POST)
	public @ResponseBody void AdminMemberUpdate(@RequestParam("u_id") String u_id,
			@RequestParam("u_pw") String u_pw,
			@RequestParam("u_name") String u_name,
			@RequestParam("u_phone") String u_phoen,
			@RequestParam("u_email") String u_email) {			
			MemberDTO dto1 = new MemberDTO();
			dto1.setU_id(u_id);
			dto1.setU_pw(u_pw);
			dto1.setU_name(u_name);
			dto1.setU_phone(u_phoen);
			dto1.setU_email(u_email);		
			System.out.println(dto1);
			mService.memberUpdate(dto1);
			System.out.println("수정완료    "+dto1);
	}

}
