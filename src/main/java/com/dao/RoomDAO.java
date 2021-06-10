package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.RoomDTO;
import com.dto.RoomInfoDTO;
import com.dto.RoomInsertDTO;
import com.dto.RoomPageDTO;
import com.dto.RoomUpdateDTO;

@Repository
public class RoomDAO {

	@Autowired
	SqlSessionTemplate template;

	public List<RoomDTO> roomList(String seq)
	{
		System.out.println("roomlist" + template);
		List<RoomDTO> list = template.selectList("HotelMapper.roomList", seq);
		return list;
	}

	public List<RoomInfoDTO> roomInfo(String seq) {
		List<RoomInfoDTO> list =  template.selectList("HotelMapper.roomInfo", seq);
		return list;
	}

	public List<RoomDTO> roomList2(String seq) {
		List<RoomDTO> list = template.selectList("HotelMapper.roomList2", seq);
		return list;
	}

	public int reserved(HashMap<String, String> map) {
		System.out.println("map: " + map + template);
		int n = template.selectOne("HotelMapper.reserved",map);
		System.out.println("roomdao" + n);
		return n;
	}

	public int selectMaxGuest(String roomseq) {
		int MaxGuest = template.selectOne("HotelMapper.selectMaxGuest",roomseq);
		return MaxGuest;
	}

	public int roomDelete(String seq) {
		int n = template.delete("HotelMapper.RoomDelete",seq);
		return n;
	}

	public RoomDTO roomSelect(String seq) {
		RoomDTO rdto = template.selectOne("HotelMapper.roomSelect", seq);
		return rdto;
	}

	public int roomUpdate(RoomUpdateDTO ruDTO) {
		int n = template.update("HotelMapper.RoomUpdate",ruDTO);
		return n;
	}

	public int roomInsertGo() {
		int room_seq = template.selectOne("HotelMapper.roomInsertGo");
		return room_seq;
	}
	public int roomInsert(RoomInsertDTO riDTO) {
		int n = template.insert("HotelMapper.roomInsert", riDTO);
		return n;
	}

	public int roomInfoInsert(RoomInfoDTO rifDTO) {
		int n1 = template.insert("HotelMapper.roomInfoInsert", rifDTO);
		return n1;
	}

	public RoomPageDTO roomListpage(int curPage, String seq) {
		RoomPageDTO RpDTO = new RoomPageDTO();
		int perPage = RpDTO.getPerPage();
		int offset = (curPage-1)*perPage;
		List<RoomDTO> list = template.selectList("HotelMapper.roomListpage", seq, new RowBounds(offset,perPage));
		RpDTO.setCurPage(curPage);//현재 페이지
		RpDTO.setList(list);//리스트 저장
		RpDTO.setTotalCount(totalCount(seq));
		System.out.println("RpDTO\t"+RpDTO);
		return RpDTO;
	}

	private int totalCount(String seq) {
		int num = template.selectOne("HotelMapper.RoomtotalCount",seq);
		System.out.println("totalCount\t"+num);
		return num;
	}
}
