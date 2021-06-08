package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.ReplyDTO;
import com.dto.ReplyPageDTO;

@Service
public interface ReplyService {
	
	public List<ReplyDTO> list(String e_code);
	public int insert(ReplyDTO rDTO);
	public int update(ReplyDTO rDTO);
	public int delete(String c_code);
	int count(String e_code);
	
}
