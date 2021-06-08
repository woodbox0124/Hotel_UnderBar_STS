package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ReplyDAO;
import com.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	ReplyDAO dao;

	@Override
	public List<ReplyDTO> list(String e_code) {
		List<ReplyDTO> list = dao.list(e_code);
		return list;
	}

	@Override
	public int insert(ReplyDTO rDTO) {
		return dao.insert(rDTO);
	}

	@Override
	public int update(ReplyDTO replyDTO) {
		return dao.update(replyDTO);
	}

	@Override
	public int delete(String c_code) {
		return dao.delete(c_code);
	}
	@Override
	public int count(String e_code) {
		return dao.count(e_code); 
	}
	
	
}
