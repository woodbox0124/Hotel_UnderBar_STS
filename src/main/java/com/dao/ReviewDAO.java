package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ReviewCountDTO;
import com.dto.ReviewDTO;
import com.dto.ReviewPageDTO;

@Repository
public class ReviewDAO {

	@Autowired
	SqlSessionTemplate session;

	public void write(ReviewDTO rvdto) {
		// TODO Auto-generated method stub
		System.out.println("insert 확인======================"+rvdto);
		session.insert("ReviewMapper.write",rvdto);
		
	}

	
	public ReviewPageDTO reviewOrder(int curPage, String hotelname) {
		ReviewPageDTO rdto = new ReviewPageDTO();
		int perPage = rdto.getPerPage(); //10
        int offset = (curPage -1)*perPage; //0
        List<ReviewDTO> list = session.selectList("ReviewMapper.reviewOrder", hotelname, new RowBounds(offset,perPage));
        rdto.setCurPage(curPage);
        rdto.setList(list);
        rdto.setTotalCount(ReviewCount(hotelname));
        return rdto;
	}

	public void reviewDelete(int origin) {
		session.delete("ReviewMapper.reviewDelete", origin);
		
	}

	public void reviewAdminDelete(int num) {
		session.delete("ReviewMapper.reviewAdminDelete", num);
		
	}

	public void reviewUpdateUp(HashMap<Object, Object> map) {
		System.out.println(map);
		session.update("ReviewMapper.reviewUpdate", map);
		
	}

	public void reviewAdminUp(HashMap<Object, Object> map) {
		session.update("ReviewMapper.reviewAdminUp", map);
		
	}

	public void reviewAnswerUp(HashMap<Object, Object> map) {
		session.insert("ReviewMapper.reviewAnswerUp", map);
		
	}

	public List<ReviewCountDTO> reviewcount(String hotelname) {
		List<ReviewCountDTO> list = session.selectList("ReviewMapper.reviewCount", hotelname);
		return list;
	}

	public int sumcount(String hotelname) {
		int n=session.selectOne("ReviewMapper.sumCount",hotelname);
		return n;
	}


	public ReviewPageDTO reviewrating(int curPage, HashMap<Object, Object> map) {
		ReviewPageDTO rdto = new ReviewPageDTO();
		int perPage = rdto.getPerPage(); //10
        int offset = (curPage -1)*perPage; //0
        String hotelname = (String)map.get("hotelname");
        List<ReviewDTO> list = session.selectList("ReviewMapper.reviewRating",map, new RowBounds(offset,perPage));
		rdto.setCurPage(curPage);
	    rdto.setList(list);
	    rdto.setTotalCount(ReviewCount(hotelname));
		return rdto;
	}

	public List<ReviewCountDTO> grouprating(String hotelname) {
		List<ReviewCountDTO> list=session.selectList("ReviewMapper.groupRating",hotelname);
		return list;
	}

	public ReviewPageDTO review(int curPage, String hotelname) {
		ReviewPageDTO rdto = new ReviewPageDTO();
		int perPage = rdto.getPerPage(); //10
        int offset = (curPage -1)*perPage; //0
        List<ReviewDTO> list = session.selectList("ReviewMapper.review", hotelname, new RowBounds(offset,perPage));
        rdto.setCurPage(curPage);
        rdto.setList(list);
        rdto.setTotalCount(ReviewCount(hotelname));
        return rdto;
	}
	private int ReviewCount(String hotelname) {
        int num = session.selectOne("ReviewMapper.ReviewCount", hotelname);
        return num;
    }
}
