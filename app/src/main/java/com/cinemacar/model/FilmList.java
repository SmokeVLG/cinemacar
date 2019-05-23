package com.cinemacar.model;

import com.cinemacar.pojo.Film;

import java.util.List;

/**
 * Created by maksim_de on 08.11.2018.
 */

public class FilmList {
	private static final FilmList ourInstance = new FilmList();
	private List<Film> workouts;

	public static FilmList getInstance() {
		return ourInstance;
	}


	public List<Film> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Film> films) {
		this.workouts = films;
	}
}
