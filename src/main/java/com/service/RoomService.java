package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoomDAO;
import com.dto.AdminRoomDTO;
import com.dto.RoomDTO;
import com.dto.RoomInfoDTO;
import com.dto.RoomInsertDTO;
import com.dto.RoomPageDTO;
import com.dto.RoomUpdateDTO;

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

	public int roomUpdate(RoomUpdateDTO ruDTO) {
		int n = dao.roomUpdate(ruDTO);
		return n;
	}

	public int roomInsertGo() {
		int room_seq = dao.roomInsertGo();
		return room_seq;
	}
	public int roomInsert(RoomInsertDTO riDTO) {
		int n = dao.roomInsert(riDTO);
		return n;

	}

	public int roomInfoInsert(RoomInfoDTO rifDTO) {
		int n1 = dao.roomInfoInsert(rifDTO);
		return n1;
	}
	public RoomPageDTO roomListpage(int curPage, String seq) {
		RoomPageDTO RpDTO = dao.roomListpage(curPage,seq);
		return RpDTO;
	}


}
