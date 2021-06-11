package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ReviewDAO;
import com.dto.ReviewCountDTO;
import com.dto.ReviewDTO;
import com.dto.ReviewPageDTO;

@Service
public class ReviewService {
	
	@Autowired
	ReviewDAO dao;

	public void write(ReviewDTO rvdto) {
		// TODO Auto-generated method stub
		dao.write(rvdto);
		
	}


	public ReviewPageDTO reviewOrder(int curPage, String hotelname) {
		ReviewPageDTO rDTO = dao.reviewOrder(curPage, hotelname);
		return rDTO;
	}

	public void reviewDelete(int origin) {
		dao.reviewDelete(origin);
		
	}

	public void reviewAdminDelete(int num) {
		dao.reviewAdminDelete(num);
		
	}

	public void reviewUpdateUp(HashMap<Object, Object> map) {
		dao.reviewUpdateUp(map);
		
	}

	public void reviewAdminUp(HashMap<Object, Object> map) {
		dao.reviewAdminUp(map);
		
	}

	public void reviewAnswerUp(HashMap<Object, Object> map) {
		dao.reviewAnswerUp(map);
		
	}

	public List<ReviewCountDTO> reviewcount(String hotelname) {
		List<ReviewCountDTO> reviewcount=dao.reviewcount(hotelname);
		return reviewcount;
	}

	public int sumcount(String hotelname) {
		 int n=dao.sumcount(hotelname);
		return n;
	}


	public ReviewPageDTO reviewrating(int curPage, HashMap<Object, Object> map) {
		ReviewPageDTO reviewrating =dao.reviewrating(curPage, map);
		return reviewrating;
	}

	public List<ReviewCountDTO> groubrating(String hotelname) {
		List<ReviewCountDTO> list=dao.grouprating(hotelname);	
		return list;
				}

	public ReviewPageDTO review(int curPage, String hotelname) {
		ReviewPageDTO rdto = dao.review(curPage, hotelname);
		return rdto;
	}



	public double hotelbyrating(String hotelname) {
		double n=dao.hotelbyrating(hotelname);
		return n;
	}


	public void hotelupdate(HashMap<Object, Object> map) {
		dao.hotelupdate(map);
		
	}

}
