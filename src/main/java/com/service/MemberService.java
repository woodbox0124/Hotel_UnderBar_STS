package com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;
	
	public String idSearch(MemberDTO mdto) {
		String u_id = dao.idSearch(mdto);
		return u_id;
	}

	public String pwSearch(MemberDTO mdto) {
		String u_pw = dao.pwSearch(mdto);
		return u_pw;
	}

	public int MemberAdd(MemberDTO mdto) {
		int n = dao.MemberAdd(mdto);
		return n;
	}

	public MemberDTO login(HashMap<String, String> map) {
		System.out.println(map);
		MemberDTO dto = dao.login(map);
		return dto;
	}

	public int idCheck(String u_id) {
		int n = dao.idCheck(u_id);
		return n;
	}

	public int pwchk(String u_pw) {
		int n = dao.pwchk(u_pw);
		return n;
	}

	public MemberDTO myPage(String u_id) {
		MemberDTO mdto = dao.myPage(u_id);
		return mdto;
	}

	public void memberUpdate(MemberDTO dto1) {
		dao.memberUpdate(dto1);
		
	}

	public void MemberDelete(String u_id) {
		dao.MemberDelete(u_id);
		
	}

	public void memberUpdate1(MemberDTO dto1) {
		dao.memberUpdate1(dto1);		
	}

	public int updatepw(Map<String, String> map) 
	{
		int a= dao.updatepw(map);
		return a;
		
	}

	public int memberCheck(MemberDTO mdto) {
		int n = dao.memberCheck(mdto);
		return n;
	}

}
