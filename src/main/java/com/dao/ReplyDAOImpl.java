package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Autowired
	SqlSessionTemplate session;
	
	

	@Override
	public List<ReplyDTO> replyList(String e_code) throws Exception {
		
		return session.selectList("ReplyMapper.replyList", e_code);
	}

	@Override
	public void addReply(ReplyDTO replyDTO) throws Exception {
		session.insert("ReplyMapper.addReply", replyDTO);
		
	}

	@Override
	public void updateReply(ReplyDTO replyDTO) throws Exception {
		session.update("ReplyMapper.updateReply", replyDTO);
		
	}

	@Override
	public void delReply(ReplyDTO replyDTO) throws Exception {
		session.delete("ReplyMapper.delReply", replyDTO);
		
	}
	
}
