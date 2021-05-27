package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.NotiDAO;
import com.dto.BoardPageDTO;
import com.dto.NotiDTO;
import com.dto.NotiPageDTO;


@Service
public class NotiService {

	@Autowired
	NotiDAO dao;

	public NotiPageDTO notiList(int curPage) {
		System.out.println("notiService : " +curPage);
		NotiPageDTO pDTO = dao.notiList(curPage);
		return pDTO;
	}

	public void notiInsert(NotiDTO dto) {
		dao.notiInsert(dto);
		
	}

	public NotiDTO notiRetrieve(int num) {
		NotiDTO nDTO = dao.notiRetrieve(num);
		return nDTO;
		
	}

	
	
	

}
