package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminRoomDTO")
public class AdminRoomDTO {
	String seq;
	String Hotelname;
	String roomname;
	int price;
	int max_guest;
	String room_img;

	public AdminRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminRoomDTO(String seq, String hotelname, String roomname, int price, int max_guest, String room_img) {
		super();
		this.seq = seq;
		Hotelname = hotelname;
		this.roomname = roomname;
		this.price = price;
		this.max_guest = max_guest;
		this.room_img = room_img;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getHotelname() {
		return Hotelname;
	}

	public void setHotelname(String hotelname) {
		Hotelname = hotelname;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
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

	@Override
	public String toString() {
		return "AdminRoomDTO [seq=" + seq + ", Hotelname=" + Hotelname + ", roomname=" + roomname + ", price=" + price
				+ ", max_guest=" + max_guest + ", room_img=" + room_img + "]";
	}

}
