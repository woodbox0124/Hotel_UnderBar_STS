package com.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dto.ResvDTO;
import com.dto.ResvMyDTO;
import com.dto.ResvPageDTO;

@Repository
public class ResvDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public int resvInsert(ResvDTO dto) {
		System.out.println("DAO : " + dto);
		int n = session.insert("ResvMapper.resvInsert", dto);
		return n;
	}
	
	public List<ResvMyDTO> resvMy(String u_id) {
		List<ResvMyDTO> list = session.selectList("ResvMapper.resvMy", u_id);
		return list;
	}
	private int totalCount(String u_id) {
		int num = session.selectOne("ResvMapper.totalCount",u_id);
		System.out.println("totalCount\t"+num);
		return num; 
	}

	public ResvPageDTO resvMyList(int curPage, String u_id) {
		ResvPageDTO RpDTO = new ResvPageDTO();
		int perPage = RpDTO.getPerPage();
		int offset = (curPage-1)*perPage;
		List<ResvMyDTO> list = session.selectList("ResvMapper.resvMyList", u_id, new RowBounds(offset,perPage));
		RpDTO.setCurPage(curPage);//현재 페이지
		RpDTO.setList(list);//리스트 저장
		RpDTO.setTotalCount(totalCount(u_id));//검색어도 넘겨서 검색에 관련 전체 레코드 개수 구하기
		System.out.println("RpDTO\t"+RpDTO);
		return RpDTO;
	}
	
	public void resvCancel(int seq) {
		session.delete("ResvMapper.resvCancel",seq);
	}

	public void resvCheckout(int seq) {
		session.update("ResvMapper.checkout", seq);
		
	}

}
