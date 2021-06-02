package com.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.BoardPageDTO;
import com.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public String idSearch(MemberDTO mdto) {
		String u_id = session.selectOne("MemberMapper.idSearch", mdto);
		return u_id;
	}

	public String pwSearch(MemberDTO mdto) {
		String u_pw = session.selectOne("MemberMapper.pwSearch", mdto);
		return u_pw;
	}

	public int MemberAdd(MemberDTO mdto) {
		int n = session.insert("MemberMapper.memberAdd", mdto);
		return n;
	}

	public MemberDTO login(HashMap<String, String> map) {
		MemberDTO dto = session.selectOne("MemberMapper.login", map);
		System.out.println(dto);
		return dto;
	}

	public int idCheck(String u_id) {
		int n = session.selectOne("MemberMapper.idCheck", u_id);
		return n;
	}

	public int pwchk(String u_pw) {
		int n = session.selectOne("MemberMapper.pwChk", u_pw);
		return n;
	}

	public MemberDTO myPage(String u_id) {
		MemberDTO mdto = session.selectOne("MemberMapper.myPage",u_id);
		return mdto;
	}

	public void memberUpdate(MemberDTO dto1) {
		session.update("MemberMapper.memberUpdate",dto1);
		
	}

	public void MemberDelete(String u_id) {
		session.delete("MemberMapper.memberDelete", u_id);
		
	}

	public void memberUpdate1(MemberDTO dto1) {
		session.update("MemberMapper.memberUpdate1",dto1);
	}


}