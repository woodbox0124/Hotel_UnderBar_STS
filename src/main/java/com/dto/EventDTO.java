package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("EventDTO")
public class EventDTO {

	private String code;
	private String subject;
	private String author;
	private String content;
	private String eventImg;
	private String regdate;
	public EventDTO() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getEventImg() {
		return eventImg;
	}
	public void setEventImg(String eventImg) {
		this.eventImg = eventImg;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public EventDTO(String code, String subject, String author, String content, String eventImg, String regdate) {
		super();
		this.code = code;
		this.subject = subject;
		this.author = author;
		this.content = content;
		this.eventImg = eventImg;
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "EventDTO [code=" + code + ", subject=" + subject + ", author=" + author + ", content=" + content
				+ ", eventImg=" + eventImg + ", regdate=" + regdate + "]";
	}
	
	
	
}