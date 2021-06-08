package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HotelDAO;
import com.dto.HotelDTO;
import com.dto.HotelUpdateDTO;
import com.dto.PageDTO;
import com.dto.RatingDTO;

@Service
public class HotelService {

	@Autowired
	HotelDAO dao;

	public PageDTO hotelList1(int curPage, String location) {
		PageDTO pDTO=dao.hotelList1(curPage,location);
		return pDTO;
	}

	public PageDTO hotelList1(int curPage) {
		PageDTO PDTO=dao.hotelList1(curPage);
		return PDTO;
	}

	public List<RatingDTO> rating() {
		List<RatingDTO> list=dao.rating();
		return list;
	}

	public int HotelDelete(String seq) {
		int n = dao.HotelDelete(seq);
	    return n;
		
	}

	public HotelDTO hotelSelect(String seq) {
		HotelDTO hDTO= dao.hotelSelect(seq);
		return hDTO;
	}

	public int hotelUpdate(HotelUpdateDTO huDTO) {
		int n = dao.hotelUpdate(huDTO);
		return n;
		
	}

	public int hotelInsertGO() {
		int seq = dao.hotelInsertGO();
		return seq;
	}

	



	
}
