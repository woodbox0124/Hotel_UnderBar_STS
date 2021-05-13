package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}