package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String member() {
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

}
