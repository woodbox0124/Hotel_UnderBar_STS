package com.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Alias("HotelInsertDTO")
public class HotelInsertDTO {
	 String name; //이름
	 String place; //지역
	 String addr; //주소
	 String hotel_img;
	 CommonsMultipartFile theFile; //파일
	public HotelInsertDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HotelInsertDTO(String name, String place, String addr, String hotel_img, CommonsMultipartFile theFile) {
		super();
		this.name = name;
		this.place = place;
		this.addr = addr;
		this.hotel_img = hotel_img;
		this.theFile = theFile;
	}
	@Override
	public String toString() {
		return "HotelInsertDTO [name=" + name + ", place=" + place + ", addr=" + addr + ", hotel_img=" + hotel_img
				+ ", theFile=" + theFile + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getHotel_img() {
		return hotel_img;
	}
	public void setHotel_img(String hotel_img) {
		this.hotel_img = hotel_img;
	}
	public CommonsMultipartFile getTheFile() {
		return theFile;
	}
	public void setTheFile(CommonsMultipartFile theFile) {
		this.theFile = theFile;
	}
	 

}
