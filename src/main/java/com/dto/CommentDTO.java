package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("CommentDTO")
public class CommentDTO {

	private String c_code;
	private String e_code;
	private String comments;
	private String writer;
	private String regdate;
	public CommentDTO() {
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
	public CommentDTO(String c_code, String e_code, String comments, String writer, String regdate) {
		super();
		this.c_code = c_code;
		this.e_code = e_code;
		this.comments = comments;
		this.writer = writer;
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "CommentDTO [c_code=" + c_code + ", e_code=" + e_code + ", comments=" + comments + ", writer=" + writer
				+ ", regdate=" + regdate + "]";
	}
	
	
	
	
}