package com.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.NotiDAO;
import com.dto.FaqDTO;
import com.dto.NotiDTO;
import com.dto.NotiPageDTO;


@Service
public class NotiService {

	@Autowired
	NotiDAO dao;

	public NotiPageDTO notiList(int curPage) {
		System.out.println("notiService : " +curPage);
		NotiPageDTO pDTO = dao.notiList(curPage);
		return pDTO;
	}

	public void notiInsert(NotiDTO dto) {
		dao.notiInsert(dto);
		
	}

	public NotiDTO notiRetrieve(int num) {
		NotiDTO nDTO = dao.notiRetrieve(num);
		return nDTO;
		
	}

	public void notiUpdate(NotiDTO nDTO) {
		System.out.println(nDTO+"넘겨준 nDTO");
		dao.notiUpdate(nDTO);
		
	}

	public void notiDelete(int num) {
		dao.notiDelete(num);
		
	}

	public void updateHit(int num) {
		dao.updateHit(num);
	}

	public void faqInsert(FaqDTO dto) {
		dao.faqInsert(dto);
	}

	public List<FaqDTO> faqList() {
		List<FaqDTO> fList = dao.faqList();
		return fList;
	}

	public void faqDelete(String code) {
		dao.faqDelete(code);
	}

//	public void faqUpdate(FaqDTO dto) {
//		dao.faqUpdate(dto);
//	}
//
//	

	
	
	

}
