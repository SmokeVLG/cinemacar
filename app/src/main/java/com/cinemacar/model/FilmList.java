package com.cinemacar.model;

import com.cinemacar.pojo.Film;

import java.util.List;

/**
 * Created by maksim_de on 08.11.2018.
 */

public class FilmList {
	private static final FilmList ourInstance = new FilmList();
	private List<Film> films;

	public static FilmList getInstance() {
		return ourInstance;
	}


	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}
}
