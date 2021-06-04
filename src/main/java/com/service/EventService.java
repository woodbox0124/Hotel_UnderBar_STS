package com.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EventDAO;
import com.dto.ReplyDTO;
import com.dto.EventDTO;
import com.dto.EventPageDTO;


@Service
public class EventService {

	@Autowired
	EventDAO dao;

	

	public void eventInsert(EventDTO dto) {
		dao.eventInsert(dto);
	}

	public EventPageDTO eventList(int curPage) {
		EventPageDTO epDTO = dao.eventList(curPage);
		return epDTO;
	}

	

	public EventDTO eventRetrieve(String code) {
		EventDTO eDTO = dao.eventRetrieve(code);
		return eDTO;
	}


	

}
