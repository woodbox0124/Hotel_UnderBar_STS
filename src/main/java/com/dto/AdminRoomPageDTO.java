package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("AdminRoomPageDTO")
public class AdminRoomPageDTO {

	private List<AdminRoomDTO> list; // 페이지번호(curPage)에 해당하는 perPage개수만큼만
	// db에서 가져와서 리스트에 저장
	private int curPage; // 현재 또는 요청 페이지
	private int perPage = 6; // 한페이지에 보여질 레코드 개수
	private int totalCount; // 전체 레코드 갯수

	public AdminRoomPageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminRoomPageDTO(List<AdminRoomDTO> list, int curPage, int perPage, int totalCount) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}

	public List<AdminRoomDTO> getList() {
		return list;
	}

	public void setList(List<AdminRoomDTO> list) {
		this.list = list;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "AdminRoomPageDTO [list=" + list + ", curPage=" + curPage + ", perPage=" + perPage + ", totalCount="
				+ totalCount + "]";
	}

	

}
