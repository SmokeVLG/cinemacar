package com.cinemacar.pojo;

import java.util.List;

public class Film {

	private String date;
	private List<Time> times;

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}
	//private String description;
	//private String pic;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/*public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}*/
}
