package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.ReplyDTO;

@Service
public interface ReplyService {
	
	
	
	List<ReplyDTO> replyList(String e_code) throws Exception;
	
	void addReply(ReplyDTO replyDTO) throws Exception;
	
	void updateReply(ReplyDTO replyDTO) throws Exception;
	
	void delReply(ReplyDTO replyDTO) throws Exception;
}
