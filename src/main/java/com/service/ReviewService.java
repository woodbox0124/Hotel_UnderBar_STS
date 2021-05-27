package com.service;

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

}
