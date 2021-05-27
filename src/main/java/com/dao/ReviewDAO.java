package com.dao;

import java.util.List;

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

	public List<ReviewDTO> review(String hotelname) {
		System.out.println(hotelname);
		List<ReviewDTO> list = session.selectList("ReviewMapper.review", hotelname);
		System.out.println(list);
		return list;
	}

	public List<ReviewDTO> reviewOrder(String hotelname) {
		List<ReviewDTO> list = session.selectList("ReviewMapper.reviewOrder", hotelname);
		return list;
	}

	public void reviewDelete(int origin) {
		session.delete("ReviewMapper.reviewDelete", origin);
		
	}

	public void reviewAdminDelete(int num) {
		session.delete("ReviewMapper.reviewAdminDelete", num);
		
	}
}
