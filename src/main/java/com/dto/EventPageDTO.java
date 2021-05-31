package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("EventPageDTO")
public class EventPageDTO {

	private List<EventDTO> list;
	private int curPage;
	private int perPage=8;   //한 페이지당 보일 레코드 개수 
	private int totalCount;
	public List<EventDTO> getList() {
		return list;
	}
	public void setList(List<EventDTO> list) {
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
		return "EventPageDTO [list=" + list + ", curPage=" + curPage + ", perPage=" + perPage + ", totalCount="
				+ totalCount + "]";
	}
	public EventPageDTO() {
		super();
	}
	public EventPageDTO(List<EventDTO> list, int curPage, int perPage, int totalCount) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}
	
	
	
	
	
}