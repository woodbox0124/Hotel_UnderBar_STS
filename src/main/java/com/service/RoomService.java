package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoomDAO;
import com.dto.AdminRoomDTO;
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

	public int reserved(HashMap<String, String> map) {
		int n = 0;
		System.out.println(dao);
		  try {
			  n = dao.reserved(map);
		}
		  catch (Exception e)
		  {
			  System.out.println("=========="); e.printStackTrace();
		  }

		return n;
	}

	public int selectMaxGuest(String roomseq) {
		int MaxGuest = dao.selectMaxGuest(roomseq);
		return MaxGuest;
	}

	public int roomDelete(String seq) {
		int n = dao.roomDelete(seq);
	    return n;
	}

	public RoomDTO roomSelect(String seq) {
		RoomDTO rDTO= dao.roomSelect(seq);
		return rDTO;
	}

}
