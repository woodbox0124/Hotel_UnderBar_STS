package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("RatingDTO")
public class RatingDTO {
	double rating;
	String hotelname;
	public RatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RatingDTO(long rating, String hotelname) {
		super();
		this.rating = rating;
		this.hotelname = hotelname;
	}
	public RatingDTO(double rating, String hotelname) {
		super();
		this.rating = rating;
		this.hotelname = hotelname;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	
	
}
