package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.ReplyDAO;
import com.dto.ReplyDTO;

public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyDAO dao;
	
	@Override
	public List<ReplyDTO> replyList(String e_code) throws Exception {
		
		return dao.replyList(e_code);
	}

	@Override
	public void addReply(ReplyDTO replyDTO) throws Exception {
		dao.addReply(replyDTO);
	}

	@Override
	public void updateReply(ReplyDTO replyDTO) throws Exception {
		dao.updateReply(replyDTO);
	}

	@Override
	public void delReply(ReplyDTO replyDTO) throws Exception {
		dao.delReply(replyDTO);
	}

}
