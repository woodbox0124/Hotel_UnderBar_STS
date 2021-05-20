package com.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.RoomDTO;
import com.dto.RoomInfoDTO;

@Repository
public class RoomDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public List<RoomDTO> roomList(String seq) 
	{
		List<RoomDTO> list = template.selectList("HotelMapper.roomList", seq);
		return list;
	}

	public List<RoomInfoDTO> roomInfo(String seq) {
		List<RoomInfoDTO> list =  template.selectList("HotelMapper.roomInfo", seq);
		return list;
	}

	public List<RoomDTO> roomList2(String seq) 
	{
		List<RoomDTO> list = template.selectList("HotelMapper.roomList2", seq);
		return list;
	}

	public int date(HashMap<String, String> map) {
		int n = template.selectOne("HotelMapper.date",map);
		return n;
	}



}
