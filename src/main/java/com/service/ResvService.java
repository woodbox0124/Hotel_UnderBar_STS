package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.dao.ResvDAO;
import com.dto.ResvDTO;
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
		ResvPageDTO RPdto = dao.resvMyList(curPage, u_id);
		System.out.println("resvMyList : " + RPdto);
		return RPdto;
	}

	public void resvCancel(int seq) {
		dao.resvCancel(seq);
	}

	public int resvInsert(ResvDTO dto) {
		System.out.println("service : " + dto);
		int n = dao.resvInsert(dto);
		return n;
	}

	public List<ResvDTO> payList(String roomseq) {
		List<ResvDTO> rdto = dao.payList(roomseq);
		System.out.println(rdto);
		return rdto;
	}




}



