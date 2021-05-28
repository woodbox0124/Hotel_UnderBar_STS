package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReviewDTO")
public class ReviewDTO {
	int num;
	String title;
	String u_id;
	String content;
	String writedate;
	int origin;
	int groupnum;
	String hotelname;
	int rating;
	String review_img;
	public ReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public int getGroupnum() {
		return groupnum;
	}
	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview_img() {
		return review_img;
	}
	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}
	public ReviewDTO(int num, String title, String u_id, String content, String writedate, int origin, int groupnum,
			String hotelname, int rating, String review_img) {
		super();
		this.num = num;
		this.title = title;
		this.u_id = u_id;
		this.content = content;
		this.writedate = writedate;
		this.origin = origin;
		this.groupnum = groupnum;
		this.hotelname = hotelname;
		this.rating = rating;
		this.review_img = review_img;
	}
	@Override
	public String toString() {
		return "ReviewDTO [num=" + num + ", title=" + title + ", u_id=" + u_id + ", content=" + content + ", writedate="
				+ writedate + ", origin=" + origin + ", groupnum=" + groupnum + ", hotelname=" + hotelname + ", rating="
				+ rating + ", review_img=" + review_img + "]";
	}
	
	

	
}
