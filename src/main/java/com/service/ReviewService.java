package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ReviewDAO;
import com.dto.ReviewCountDTO;
import com.dto.ReviewDTO;

@Service
public class ReviewService {
	
	@Autowired
	ReviewDAO dao;

	public void write(ReviewDTO rvdto) {
		// TODO Auto-generated method stub
		dao.write(rvdto);
		
	}

	public List<ReviewDTO> review(String hotelname) {
		List<ReviewDTO> list = dao.review(hotelname);
		return list;
	}

	public List<ReviewDTO> reviewOrder(String hotelname) {
		List<ReviewDTO> list = dao.reviewOrder(hotelname);
		return list;
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


	public List<ReviewDTO> reviewrating(HashMap<Object, Object> map) {
		List<ReviewDTO> reviewrating=dao.reviewrating(map);
		return reviewrating;
	}

}
