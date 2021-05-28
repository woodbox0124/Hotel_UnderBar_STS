package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ReviewDAO;
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

}
