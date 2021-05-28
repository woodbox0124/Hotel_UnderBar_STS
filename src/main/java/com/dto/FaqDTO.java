package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("FaqDTO")
public class FaqDTO {

	private int num;
	private String subject;
	private String content;
	
	public FaqDTO() {
		super();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FaqDTO(int num, String subject, String content) {
		super();
		this.num = num;
		this.subject = subject;
		this.content = content;
	}

	@Override
	public String toString() {
		return "FaqDTO [num=" + num + ", subject=" + subject + ", content=" + content + "]";
	}
	
}