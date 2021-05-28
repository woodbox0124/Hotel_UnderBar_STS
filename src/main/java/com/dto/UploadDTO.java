package com.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadDTO { //리뷰내용받는데 필요한 dto
	String title; //제목
	String u_id; //글쓴이
	String content; //내용
	int star; //평점
	String hotelname; //호텔이름
	CommonsMultipartFile theFile; //파일
	public UploadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadDTO(String title, String u_id, String content, int star, String hotelname,
			CommonsMultipartFile theFile) {
		super();
		this.title = title;
		this.u_id = u_id;
		this.content = content;
		this.star = star;
		this.hotelname = hotelname;
		this.theFile = theFile;
	}
	@Override
	public String toString() {
		return "UploadDTO [title=" + title + ", u_id=" + u_id + ", content=" + content + ", star=" + star
				+ ", hotelname=" + hotelname + ", theFile=" + theFile + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public CommonsMultipartFile getTheFile() {
		return theFile;
	}
	public void setTheFile(CommonsMultipartFile theFile) {
		this.theFile = theFile;
	}
	
}
