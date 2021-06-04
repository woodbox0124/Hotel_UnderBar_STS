package com.dao;

import java.util.List;

import com.dto.ReplyDTO;

public interface ReplyDAO {
	List<ReplyDTO> replyList(String e_code) throws Exception;
	
	void addReply(ReplyDTO replyDTO) throws Exception;
	
	void updateReply(ReplyDTO replyDTO) throws Exception;
	
	void delReply(ReplyDTO replyDTO) throws Exception;
}
