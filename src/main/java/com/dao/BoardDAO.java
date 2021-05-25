package com.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void layerUpdate(int origin, int groupnum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("origin", origin);
		map.put("groupnum", groupnum);
		session.update("BoardMapper.updateLayer", map);
	}
	

	public void boardUpdate(BoardDTO bDTO) {
		session.update("BoardMapper.updateBoard", bDTO);
		System.out.println("BoardDAO : "+bDTO);
	}

	public void boardDelete(int num) {
		session.delete("BoardMapper.boardDelete", num);
	}

	public int boardAnsInsert(BoardDTO bDTO) {
		int num = bDTO.getNum();
		int origin = bDTO.getOrigin();
		int groupnum = bDTO.getGroupnum();
		int grouplayer = bDTO.getGrouplayer();
		if(num!=0) {
			layerUpdate(origin, groupnum );
			groupnum++;
			grouplayer++;
		}
		bDTO.setOrigin(origin);
		bDTO.setGroupnum(groupnum);
		bDTO.setGrouplayer(grouplayer);
		System.out.println("BOARD INSERT : "+bDTO);
		int n = session.insert("BoardMapper.boardAnsInsert", bDTO);
		System.out.println("after Insert"+bDTO);
		return n;
	}

	public void readCnt(int num) {
		session.update("BoardMapper.readCnt", num);
		
	}
	public BoardDTO selectByNum(int num) {
		BoardDTO bDTO = session.selectOne("BoardMapper.selectByNum", num);
		return bDTO;
	}
}