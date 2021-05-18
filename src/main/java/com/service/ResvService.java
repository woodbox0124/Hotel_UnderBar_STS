package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ResvDAO;
import com.dto.ResvMyDTO;
import com.dto.ResvPageDTO;

@Service
public class ResvService {

	@Autowired
	ResvDAO dao;

	public List<ResvMyDTO> resvMy(String u_id) {
		List<ResvMyDTO> list = dao.resvMy(u_id);
		return list;
	}

	public ResvPageDTO resvMyList(int curPage, String u_id) {
		ResvPageDTO dto = dao.resvMyList(curPage, u_id);
		System.out.println("ResvService" + dto);
		return dto;
	}

}
