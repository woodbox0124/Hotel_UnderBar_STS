package com.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BoardDAO;
import com.dto.BoardPageDTO;


@Service
public class BoardService {

	@Autowired
	BoardDAO dao;

	public BoardPageDTO boardList(int curPage, HashMap<String, String> map) {
		BoardPageDTO pDTO = dao.boardList(curPage, map);
		System.out.println(pDTO);
		
		return pDTO;
	}
	
	

}
