package com.cinemacar.interfaces;

import com.cinemacar.pojo.Film;

import java.util.ArrayList;

public interface IListFilmPresenter {
	void setSuccessLoadFilms(ArrayList<Film> films);

	void setFailLoadFilms(String key);
}
