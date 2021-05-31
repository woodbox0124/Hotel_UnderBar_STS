package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("FaqDTO")
public class FaqDTO {

	private String code;
	private String subject;
	private String content;
	
	public FaqDTO() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setNum(String code) {
		this.code = code;
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

	public FaqDTO(String code, String subject, String content) {
		super();
		this.code = code;
		this.subject = subject;
		this.content = content;
	}

	@Override
	public String toString() {
		return "FaqDTO [code=" + code + ", subject=" + subject + ", content=" + content + "]";
	}
	
}