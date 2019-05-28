package com.cinemacar.model;

import com.cinemacar.pojo.Day;

import java.util.List;


public class ListDays {
	public static String TAG = ListDays.class.getSimpleName();


	private static final ListDays ourInstance = new ListDays();
	private List<Day> days;

	public static ListDays getInstance() {
		return ourInstance;
	}


	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}
}
