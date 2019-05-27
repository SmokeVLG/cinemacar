package com.cinemacar.model;

import com.cinemacar.fragments.ListFilmFragment;
import com.cinemacar.pojo.Film;

import java.util.List;


public class ListFilm {
	public static String TAG = ListFilm.class.getSimpleName();


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
