package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReviewCountDTO")
public class ReviewCountDTO {
int numrating;
double count;
public ReviewCountDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public ReviewCountDTO(int numrating, double count) {
	super();
	this.numrating = numrating;
	this.count = count;
}
@Override
public String toString() {
	return "ReviewCountDTO [numrating=" + numrating + ", count=" + count + "]";
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


}
