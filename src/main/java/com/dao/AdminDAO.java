package com.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class AdminDAO {
	
	@Autowired
	SqlSessionTemplate template;

	public List<MemberDTO> serachName(HashMap<String, String> map) {
		List<MemberDTO> dto = template.selectList("AdminMapper.serachName", map);
		return dto;
	}

}
