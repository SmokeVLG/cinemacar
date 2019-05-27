package com.cinemacar.interfaces;

import com.cinemacar.pojo.Film;

import java.util.ArrayList;

public interface ListFilmLoadPresenterInterface {
	void success(ArrayList<Film> films);

	void fail(String key);
}
