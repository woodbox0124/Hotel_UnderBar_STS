package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("HotelDTO")
public class HotelDTO {
	private String seq;
	private String name;
	private String place;
	private String addr;
	private double rating;
	private String hotel_img;
	private String hotel_img_real;
	private String maplocation1;
	private String maplocation2;
	
	public HotelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelDTO(String seq, String name, String place, String addr, double rating, String hotel_img,
			String hotel_img_real, String maplocation1, String maplocation2) {
		super();
		this.seq = seq;
		this.name = name;
		this.place = place;
		this.addr = addr;
		this.rating = rating;
		this.hotel_img = hotel_img;
		this.hotel_img_real = hotel_img_real;
		this.maplocation1 = maplocation1;
		this.maplocation2 = maplocation2;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getHotel_img() {
		return hotel_img;
	}

	public void setHotel_img(String hotel_img) {
		this.hotel_img = hotel_img;
	}

	public String getHotel_img_real() {
		return hotel_img_real;
	}

	public void setHotel_img_real(String hotel_img_real) {
		this.hotel_img_real = hotel_img_real;
	}

	public String getMaplocation1() {
		return maplocation1;
	}

	public void setMaplocation1(String maplocation1) {
		this.maplocation1 = maplocation1;
	}

	public String getMaplocation2() {
		return maplocation2;
	}

	public void setMaplocation2(String maplocation2) {
		this.maplocation2 = maplocation2;
	}

	@Override
	public String toString() {
		return "HotelDTO [seq=" + seq + ", name=" + name + ", place=" + place + ", addr=" + addr + ", rating=" + rating
				+ ", hotel_img=" + hotel_img + ", hotel_img_real=" + hotel_img_real + ", maplocation1=" + maplocation1
				+ ", maplocation2=" + maplocation2 + "]";
	}
	
	
	
}