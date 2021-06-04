package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReviewCountDTO")
public class ReviewCountDTO {
int numrating;
double count;
int gc;
public ReviewCountDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public ReviewCountDTO(int numrating, double count, int gc) {
	super();
	this.numrating = numrating;
	this.count = count;
	this.gc = gc;
}
@Override
public String toString() {
	return "ReviewCountDTO [numrating=" + numrating + ", count=" + count + ", gc=" + gc + "]";
}
public int getNumrating() {
	return numrating;
}
public void setNumrating(int numrating) {
	this.numrating = numrating;
}
public double getCount() {
	return count;
}
public void setCount(double count) {
	this.count = count;
}
public int getGc() {
	return gc;
}
public void setGc(int gc) {
	this.gc = gc;
}



}
