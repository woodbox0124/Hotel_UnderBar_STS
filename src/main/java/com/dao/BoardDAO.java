package com.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.BoardDTO;
import com.dto.BoardPageDTO;


@Repository
public class BoardDAO {
	@Autowired
	SqlSessionTemplate session;

	public BoardPageDTO boardList(int curPage, HashMap<String, String> map) {
		BoardPageDTO pDTO = new BoardPageDTO();
		int perPage = pDTO.getPerPage(); //15
		int offset = (curPage -1)*perPage; //0
		List<BoardDTO> list = session.selectList("BoardMapper.boardList", map, new RowBounds(offset,perPage));
		pDTO.setCurPage(curPage);
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount(map));
		System.out.println("BoardPageDTO : " + pDTO);
		return pDTO;
	}

	private int totalCount(HashMap<String, String> map) {
		int num = session.selectOne("BoardMapper.totalCount", map);
		System.out.println("BoardTotalCount : "+num);
		return num;
	}

	public int boardInsert(BoardDTO bDTO) {
		int n = session.insert("BoardMapper.boardInsert", bDTO);
		return n;
	}
}