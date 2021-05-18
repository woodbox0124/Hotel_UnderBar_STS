package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HotelDAO;
import com.dto.PageDTO;

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
}
