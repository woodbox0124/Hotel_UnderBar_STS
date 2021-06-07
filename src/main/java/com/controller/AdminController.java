package com.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.AdminHotelPageDTO;
import com.dto.AdminMemberPageDTO;
import com.dto.AdminRoomPageDTO;
import com.dto.HotelDTO;
import com.dto.HotelUpdateDTO;
import com.dto.MemberDTO;
import com.dto.RoomDTO;
import com.dto.RoomUpdateDTO;
import com.service.AdminService;
import com.service.HotelService;
import com.service.MemberService;
import com.service.RoomService;

@Controller
public class AdminController {
	@Autowired
	AdminService service;
	@Autowired
	MemberService mService;
	@Autowired
	HotelService hService;
	@Autowired
	RoomService rService;

	// 회원관리 페이지로 이동 합니다.
	@RequestMapping("/loginCheck/adminMember")
	public String member(@RequestParam(required = false, defaultValue = "1") String curPage,
			@RequestParam(required = false, defaultValue = "id") String searchName,
			@RequestParam(required = false, defaultValue = "") String searchValue, HttpSession session)
			throws Exception {
		System.out.println(curPage);
		System.out.println(searchName);
		System.out.println(searchValue);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		System.out.println(map);
		AdminMemberPageDTO ampDTO = service.adminMember(Integer.parseInt(curPage), map);
		System.out.println("Controller" + ampDTO);
		session.setAttribute("ampDTO", ampDTO);
		return "redirect:../adminMember";
	}

	// 회원정보 검색 기능입니다.
	@RequestMapping("/loginCheck/searchName")
	public String searchName(@RequestParam("searchName") String searchName,
			@RequestParam("searchValue") String searchValue, RedirectAttributes attr) {
		System.out.println(searchName);
		System.out.println(searchValue);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		List<MemberDTO> list = service.serachName(map);
		System.out.println(list);
		attr.addFlashAttribute("member", list);
		return "redirect:../adminMember";
	}

	// 회원정보 삭제 기능입니다.
	@RequestMapping("/loginCheck/delete")
	public @ResponseBody void delete(@RequestParam("u_id") String u_id) {
		System.out.println(u_id);
		mService.MemberDelete(u_id);
	}

	// 회원 정보 수정을 위해 자식창으로 정보 전달을 위한 기능입니다..
	@RequestMapping("/loginCheck/update")
	public String update(@RequestParam("u_id") String u_id, RedirectAttributes att) {
		System.out.println(u_id);
		MemberDTO mdto = mService.myPage(u_id);
		System.out.println(mdto);
		att.addFlashAttribute("mdto", mdto);
		return "redirect:../admin/update";
	}

	// 회원 정보 수정 후 DB 전송을 위한 기능입니다.(ajax)
	@RequestMapping(value = "/loginCheck/AdminMemberUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void AdminMemberUpdate(@RequestParam("u_id") String u_id, @RequestParam("u_pw") String u_pw,
			@RequestParam("u_name") String u_name, @RequestParam("u_phone") String u_phone,
			@RequestParam("u_email") String u_email, @RequestParam(required = false, defaultValue = "1") String curPage,
			@RequestParam(required = false, defaultValue = "id") String searchName,
			@RequestParam(required = false, defaultValue = "") String searchValue, HttpSession session) {
		MemberDTO dto1 = new MemberDTO();
		System.out.println(u_id);
		System.out.println(u_pw);
		System.out.println(u_name);
		System.out.println(u_phone);
		System.out.println(u_email);
		dto1.setU_id(u_id);
		dto1.setU_pw(u_pw);
		dto1.setU_name(u_name);
		dto1.setU_phone(u_phone);
		dto1.setU_email(u_email);
		System.out.println(dto1);
		mService.memberUpdate1(dto1);
		// 새로고침을 해도 세션에 리스트 재 저장 하여 업데이트 된 데이터 뿌림
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		System.out.println(map);
		AdminMemberPageDTO ampDTO = service.adminMember(Integer.parseInt(curPage), map);
		System.out.println("Controller" + ampDTO);
		session.setAttribute("ampDTO", ampDTO);
	}

	// 호텔관리 페이지로 이동 합니다.
	@RequestMapping("/loginCheck/adminHotel")
	public String hotel(@RequestParam(required = false, defaultValue = "1") String curPage,
			@RequestParam(required = false, defaultValue = "id") String searchName,
			@RequestParam(required = false, defaultValue = "") String searchValue, HttpSession session)
			throws Exception {
		System.out.println(curPage);
		System.out.println(searchName);
		System.out.println(searchValue);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		System.out.println(map);
		AdminHotelPageDTO ahpDTO = service.adminHotel(Integer.parseInt(curPage), map);
		System.out.println("Controller" + ahpDTO);
		session.setAttribute("ahpDTO", ahpDTO);
		return "redirect:../adminHotel";
	}

	// 호텔정보 삭제 기능입니다.
	@RequestMapping("/loginCheck/HotelDelete")
	public @ResponseBody void hDelete(@RequestParam("seq") String seq) {
		System.out.println("넘어온 호텔 아이디" + seq);
		int n = hService.HotelDelete(seq);
		System.out.println(n + "개 삭제 성공");
	}

	// 호텔 정보 수정을 위해 자식창으로 정보 전달을 위한 기능입니다.
	@RequestMapping("/loginCheck/hotelSelect")
	public String hotelSelect(@RequestParam("seq") String seq, RedirectAttributes att) {
		System.out.println(seq);
		HotelDTO hDTO = hService.hotelSelect(seq);
		System.out.println(hDTO);
		att.addFlashAttribute("hDTO", hDTO);
		return "redirect:../admin/hotelupdate";
	}

	/*
	 * // 회원 정보 수정 후 DB 전송을 위한 기능입니다.
	 * 
	 * @RequestMapping(value = "/loginCheck/HotelUpload", method =
	 * RequestMethod.POST) public String HotelUpload(HotelUpdateDTO
	 * huDTO, @RequestParam(required = false, defaultValue = "1") String curPage,
	 * 
	 * @RequestParam(required = false, defaultValue = "id") String searchName,
	 * 
	 * @RequestParam(required = false, defaultValue = "") String searchValue,
	 * HttpSession session) throws Exception { String seq = huDTO.getSeq(); String
	 * hotelname = huDTO.getName(); String place = huDTO.getPlace(); String addr =
	 * huDTO.getAddr(); String hotel_img = huDTO.getHotel_img();
	 * CommonsMultipartFile theFile = huDTO.getTheFile(); String originalFileName =
	 * theFile.getOriginalFilename(); System.out.println( seq + "\t" + hotelname +
	 * "\t" + place + "\t" + addr + "\t" + hotel_img + "\t" + originalFileName);
	 * File f = new File("C:\\upload", originalFileName); int n =
	 * hService.hotelUpdate(huDTO); System.out.println(curPage);
	 * System.out.println(searchName); System.out.println(searchValue);
	 * HashMap<String, String> map = new HashMap<String, String>();
	 * map.put("searchName", searchName); map.put("searchValue", searchValue);
	 * System.out.println(map); AdminHotelPageDTO ahpDTO =
	 * service.adminHotel(Integer.parseInt(curPage), map);
	 * System.out.println("Controller" + ahpDTO); session.setAttribute("ahpDTO",
	 * ahpDTO); session.setAttribute("updatecomplete", n); try {
	 * theFile.transferTo(f); } catch (Exception e) { e.printStackTrace(); } return
	 * "redirect:../admin/hotelupdate"; }
	 */

	// 호텔 객실관리 페이지로 이동 합니다.
	@RequestMapping("/loginCheck/adminRoom")
	public String room(@RequestParam(required = false, defaultValue = "1") String curPage,
			@RequestParam(required = false, defaultValue = "name") String searchName,
			@RequestParam(required = false, defaultValue = "") String searchValue, HttpSession session)
			throws Exception {
		System.out.println(curPage);
		System.out.println(searchName);
		System.out.println(searchValue);
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		System.out.println(map);
		AdminRoomPageDTO arpDTO = service.adminRoom(Integer.parseInt(curPage), map);
		System.out.println("Controller" + arpDTO);
		session.setAttribute("arpDTO", arpDTO);
		return "redirect:../adminRoom";
	}

	// 객실 삭제 기능
	@RequestMapping("/loginCheck/RoomDelete")
	public @ResponseBody void rDelete(@RequestParam("seq") String seq) {
		System.out.println("객실 번호  : " + seq);
		int n = rService.roomDelete(seq);
		System.out.println("삭제된 갯수 : " + n);
	}

	// 객실 정보 수정을 위해 자식창으로 정보 전달을 위한 기능입니다.
	@RequestMapping("/loginCheck/roomSelect")
	public String roomSelect(@RequestParam("seq") String seq, RedirectAttributes att) {
		System.out.println(seq);
		RoomDTO rDTO = rService.roomSelect(seq);
		System.out.println(rDTO);
		att.addFlashAttribute("rDTO", rDTO);
		return "redirect:../admin/roomupdate";
	}

}
