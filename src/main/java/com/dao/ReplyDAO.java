package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dto.ReplyDTO;
import com.dto.ReplyPageDTO;

@Repository
public interface ReplyDAO {
	public List<ReplyDTO> list(String e_code);
	public int insert(ReplyDTO rDTO);
	public int update(ReplyDTO rDTO);
	public int delete(String c_code);
	int count(String e_code);
	
	
}
