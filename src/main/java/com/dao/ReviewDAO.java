package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ReviewDTO;

@Repository
public class ReviewDAO {

	@Autowired
	SqlSessionTemplate session;

	public void write(ReviewDTO rvdto) {
		// TODO Auto-generated method stub
		session.insert("ReviewMapper.write");
		
	}
}
