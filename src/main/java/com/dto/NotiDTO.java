package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("NotiDTO")
public class NotiDTO {

	private int num;
	private String subject;
	private String author;
	private String content;
	private String regdate;
	private int hit;  //조회수
	
	public NotiDTO() {
		super();
	}

	@Override
	public String toString() {
		return "NotiDTO [num=" + num + ", subject=" + subject + ", author=" + author + ", content=" + content
				+ ", regdate=" + regdate + ", hit=" + hit + "]";
	}

	public NotiDTO(int num, String subject, String author, String content, String regdate, int hit) {
		super();
		this.num = num;
		this.subject = subject;
		this.author = author;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	

	
}