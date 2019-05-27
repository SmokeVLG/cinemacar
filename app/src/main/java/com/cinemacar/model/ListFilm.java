package com.cinemacar.model;

import com.cinemacar.pojo.Film;

import java.util.List;


public class ListFilm {
	private static final ListFilm ourInstance = new ListFilm();
	private List<Film> films;

	public static ListFilm getInstance() {
		return ourInstance;
	}


	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}
}
