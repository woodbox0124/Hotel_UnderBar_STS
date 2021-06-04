package com.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ReplyDTO;
import com.dto.EventDTO;
import com.dto.EventPageDTO;
import com.dto.FaqDTO;
import com.dto.NotiDTO;
import com.dto.NotiPageDTO;


@Repository
public class EventDAO {
	@Autowired
	SqlSessionTemplate session;


	public void eventInsert(EventDTO dto) {
		session.insert("EventMapper.eventInsert",dto);
		
	}

	public EventPageDTO eventList(int curPage) {
		EventPageDTO epDTO = new EventPageDTO();
		int perPage = epDTO.getPerPage(); //8
		int offset = (curPage -1)*perPage; //0
		List<EventDTO> list = session.selectList("EventMapper.eventList",null,new RowBounds(offset,perPage));
		epDTO.setCurPage(curPage);
		epDTO.setList(list);
		epDTO.setTotalCount(totalCount2());
		System.out.println("EventPageDTO : " + epDTO.toString());
		return epDTO;
	}
	private int totalCount2() {
		int num = session.selectOne("EventMapper.totalCount2");
		return num;
	}

	public EventDTO eventRetrieve(String code) {
		EventDTO eDTO = session.selectOne("EventMapper.eventRetrieve",code);
		return eDTO;
	}

	

	
	
	
}