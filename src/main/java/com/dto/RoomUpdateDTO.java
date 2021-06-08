package com.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Alias("RoomUpdateDTO")
public class RoomUpdateDTO {
	String seq; // 객실 번호
	String name; // 객실 이름
	int price; // 가격
	int max_guest; // 최대 인원수
	String room_img; // 객실 사진
	CommonsMultipartFile theFile; // 파일

	public RoomUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomUpdateDTO(String seq, String name, int price, int max_guest, String room_img,
			CommonsMultipartFile theFile) {
		super();
		this.seq = seq;
		this.name = name;
		this.price = price;
		this.max_guest = max_guest;
		this.room_img = room_img;
		this.theFile = theFile;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public CommonsMultipartFile getTheFile() {
		return theFile;
	}

	public void setTheFile(CommonsMultipartFile theFile) {
		this.theFile = theFile;
	}

	@Override
	public String toString() {
		return "RoomUpdateDTO [seq=" + seq + ", name=" + name + ", price=" + price + ", max_guest=" + max_guest
				+ ", room_img=" + room_img + ", theFile=" + theFile + "]";
	}

}
