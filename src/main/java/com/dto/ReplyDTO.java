package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReplyDTO")
public class ReplyDTO {

	private String c_code; //댓글번호
	private String e_code; //게시물번호
	private String comments; //내용
	private String writer; // 작성자
	private String regdate; // 작성일자
	private String updateDate; //업데이트일자
	public ReplyDTO() {
		super();
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public String getE_code() {
		return e_code;
	}
	public void setE_code(String e_code) {
		this.e_code = e_code;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public ReplyDTO(String c_code, String e_code, String comments, String writer, String regdate, String updateDate) {
		super();
		this.c_code = c_code;
		this.e_code = e_code;
		this.comments = comments;
		this.writer = writer;
		this.regdate = regdate;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "ReplyDTO [c_code=" + c_code + ", e_code=" + e_code + ", comments=" + comments + ", writer=" + writer
				+ ", regdate=" + regdate + ", updateDate=" + updateDate + "]";
	}
	
	
	
	
	
}