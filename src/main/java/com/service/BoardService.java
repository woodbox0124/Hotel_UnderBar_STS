package com.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BoardDAO;
import com.dto.BoardDTO;
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

	public int boardInsert(BoardDTO bDTO) {
		int n = dao.boardInsert(bDTO);
		return n;
	}

	
	public void boardUpdate(BoardDTO bDTO) {
		dao.boardUpdate(bDTO);
	}

	public void boardDelete(int num) {
		dao.boardDelete(num);
	}

	public int boardAnsInsert(BoardDTO bDTO) {
		int n = dao.boardAnsInsert(bDTO);
		return n;
	}

	public void readCnt(int num) {
		dao.readCnt(num);
	}
	public BoardDTO selectByNum(int num) {
		BoardDTO bDTO = dao.selectByNum(num);
		return bDTO;
	}

	
	

}
