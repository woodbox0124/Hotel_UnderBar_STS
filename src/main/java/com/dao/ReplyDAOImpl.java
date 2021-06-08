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
	public List<ReplyDTO> list(String e_code) {
		List<ReplyDTO> list = session.selectList("ReplyMapper.list",e_code);
//		ReplyPageDTO rpDTO = new ReplyPageDTO();
//		int perPage = rpDTO.getPerPage();//10
//		int offset = (curPage - 1)*perPage;//0
//		List<ReplyDTO> list = session.selectList("ReplyMapper.list",null, new RowBounds(offset,perPage));
//		rpDTO.setCurPage(curPage);
//		rpDTO.setList(list);
//		rpDTO.setTotalCount(totalCount(e_code));
		
		return list;
	}

	private int totalCount(String e_code) {
		int num = session.selectOne("ReplyMapper.totalCount", e_code);
		return num;
	}
	
	
	
	
	
	
	
	
	
	

	@Override
	public int insert(ReplyDTO rDTO) {
		return session.insert("ReplyMapper.insert",rDTO);
	}

	@Override
	public int update(ReplyDTO replyDTO) {
		return session.update("ReplyMapper.update",replyDTO);
	}

	@Override
	public int delete(String c_code) {
		return session.delete("ReplyMapper.delete",c_code);		
	}

	@Override
	public int count(String e_code) {
		return session.selectOne("ReplyMapper.countReply", e_code);
	}
	
	
}
