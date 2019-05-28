package com.cinemacar.model;

import com.cinemacar.pojo.Day;

import java.util.List;


public class ListDay {
	public static String TAG = ListDay.class.getSimpleName();


	private static final ListDay ourInstance = new ListDay();
	private List<Day> days;

	public static ListDay getInstance() {
		return ourInstance;
	}


	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}
}
