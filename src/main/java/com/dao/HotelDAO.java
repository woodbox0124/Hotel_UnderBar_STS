package com.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.HotelDTO;
import com.dto.HotelUpdateDTO;
import com.dto.PageDTO;
import com.dto.RatingDTO;

@Repository
public class HotelDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public PageDTO hotelList1(int curPage, String location) {
		PageDTO pdto=new PageDTO();
		int perPage = pdto.getPerPage();
		int offset = (curPage-1)*perPage;
		List<HotelDTO> list=template.selectList("HotelMapper.hotelLoc", location,new RowBounds(offset,perPage));
		pdto.setCurPage(curPage);//현재 페이지
		pdto.setList(list);//리스트 저장
		pdto.setTotalCount(totalCount(location));//검색어도 넘겨서 검색에 관련 전체 레코드 개수 구하기
		return pdto;
	}
	private int totalCount(String location) {
		int num = template.selectOne("HotelMapper.totalCount", location);
		System.out.println("totalCount\t"+num);
		return num; 
	}
	public PageDTO hotelList1(int curPage) {
		PageDTO pDTO = new PageDTO();
		int perPage = pDTO.getPerPage();
		int offset = (curPage-1)*perPage;
		List<HotelDTO> list = template.selectList("HotelMapper.hotelList", null, new RowBounds(offset,perPage));
		pDTO.setCurPage(curPage);//현재 페이지
		pDTO.setList(list);//리스트 저장
         pDTO.setTotalCount(totalCount());//검색어도 넘겨서 검색에 관련 전체 레코드 개수 구하기
	     return pDTO;
	}
	private int totalCount() {
		int num = template.selectOne("HotelMapper.totalCount1");
		System.out.println("totalCount\t"+num);
		return num; 
	}
	public List<RatingDTO> rating() {
		List<RatingDTO> list=template.selectList("HotelMapper.rating");
		return list;
	}
	public int HotelDelete(String seq) {
		int n= template.delete("HotelMapper.HotelDelete", seq);
		return n;
	}
	public HotelDTO hotelSelect(String seq) {
		HotelDTO hdto = template.selectOne("HotelMapper.hotelSelect", seq);
		return hdto;
	}
	public int hotelUpdate(HotelUpdateDTO huDTO) {
		int n = template.update("HotelMapper.hotelUpdate",huDTO);
		return n;
	}

	
}
