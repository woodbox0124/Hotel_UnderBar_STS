package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoomDAO;
import com.dto.RoomDTO;
import com.dto.RoomInfoDTO;

@Service
public class RoomService 
{
	@Autowired
	RoomDAO dao;

	public List<RoomDTO> roomList(String seq)
	{
		List<RoomDTO> list =  dao.roomList(seq);
		return list;
	}

	public List<RoomInfoDTO> roomInfo(String seq) 
	{
		List<RoomInfoDTO> list = dao.roomInfo(seq);
		return list;
	}

	public List<RoomDTO> roomList2(String seq) 
	{
		List<RoomDTO> list = dao.roomList2(seq);
		return list;
	}

	public int date(HashMap<String, String> map) {
		int n = 0;
		try {
			RoomDAO dao = new RoomDAO();
			n = dao.date(map);
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		return n;
	}

}
