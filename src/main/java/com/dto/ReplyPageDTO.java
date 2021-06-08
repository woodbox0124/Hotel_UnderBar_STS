package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;
@Alias("ReplyPageDTO")
public class ReplyPageDTO {

	private List<ReplyDTO> list;
	private int curPage;
	private int perPage=10;   //한 페이지당 보일 레코드 개수 
	private int totalCount;
	public List<ReplyDTO> getList() {
		return list;
	}
	public void setList(List<ReplyDTO> list) {
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
		return "ReplyPageDTO [list=" + list + ", curPage=" + curPage + ", perPage=" + perPage + ", totalCount="
				+ totalCount + "]";
	}
	public ReplyPageDTO(List<ReplyDTO> list, int curPage, int perPage, int totalCount) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}
	public ReplyPageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
