package com.dao;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.NotiDTO;
import com.dto.NotiPageDTO;


@Repository
public class NotiDAO {
	@Autowired
	SqlSessionTemplate session;

	public NotiPageDTO notiList(int curPage) {
		NotiPageDTO pDTO = new NotiPageDTO();
		int perPage = pDTO.getPerPage(); //15
		int offset = (curPage -1)*perPage; //0
		List<NotiDTO> list = session.selectList("NotiMapper.notiList",new RowBounds(offset,perPage));
		pDTO.setCurPage(curPage);
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount());
		System.out.println("NotiPageDTO : " + pDTO.toString());
		return pDTO;
	}

	private int totalCount() {
		int num = session.selectOne("NotiMapper.totalCount");
		return num;
	}

	public void notiInsert(NotiDTO dto) {
		session.insert("NotiMapper.notiInsert",dto);
		System.out.println(dto);
	}

	public NotiDTO notiRetrieve(int num) {
		System.out.println("NotiDAO num : "+num);
		NotiDTO nDTO = session.selectOne("NotiMapper.notiRetrieve",num);
		return nDTO;
	}
	
	
	
}