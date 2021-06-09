package com.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Alias("RoomInsertDTO")
public class RoomInsertDTO {	 
	 String name; //방이름
	 int hotelseq; //호텔고유 번호
	 int price; //가격
	 int max_guest;//최대인원수
	 String room_img;//방사진
	 String room_img_real;//방사진2
	 CommonsMultipartFile theFile; //파일
	 CommonsMultipartFile theFile1; //파일
	 
	public RoomInsertDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoomInsertDTO(String name, int hotelseq, int price, int max_guest, String room_img, String room_img_real,
			CommonsMultipartFile theFile, CommonsMultipartFile theFile1) {
		super();
		this.name = name;
		this.hotelseq = hotelseq;
		this.price = price;
		this.max_guest = max_guest;
		this.room_img = room_img;
		this.room_img_real = room_img_real;
		this.theFile = theFile;
		this.theFile1 = theFile1;
	}
	@Override
	public String toString() {
		return "RoomInsertDTO [name=" + name + ", hotelseq=" + hotelseq + ", price=" + price + ", max_guest="
				+ max_guest + ", room_img=" + room_img + ", room_img_real=" + room_img_real + ", theFile=" + theFile
				+ ", theFile1=" + theFile1 + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHotelseq() {
		return hotelseq;
	}
	public void setHotelseq(int hotelseq) {
		this.hotelseq = hotelseq;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMax_guest() {
		return max_guest;
	}
	public void setMax_guest(int max_guest) {
		this.max_guest = max_guest;
	}
	public String getRoom_img() {
		return room_img;
	}
	public void setRoom_img(String room_img) {
		this.room_img = room_img;
	}
	public String getRoom_img_real() {
		return room_img_real;
	}
	public void setRoom_img_real(String room_img_real) {
		this.room_img_real = room_img_real;
	}
	public CommonsMultipartFile getTheFile() {
		return theFile;
	}
	public void setTheFile(CommonsMultipartFile theFile) {
		this.theFile = theFile;
	}
	public CommonsMultipartFile getTheFile1() {
		return theFile1;
	}
	public void setTheFile1(CommonsMultipartFile theFile1) {
		this.theFile1 = theFile1;
	}
	 
}
