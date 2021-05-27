package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;
@Alias("NotiPageDTO")
public class NotiPageDTO {

	private List<NotiDTO> list;
	private int curPage;
	private int perPage=15;   //한 페이지당 보일 레코드 개수 
	private int totalCount;
	public List<NotiDTO> getList() {
		return list;
	}
	public void setList(List<NotiDTO> list) {
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
	public NotiPageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotiPageDTO(List<NotiDTO> list, int curPage, int perPage, int totalCount) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}
	
	
	
	
}
