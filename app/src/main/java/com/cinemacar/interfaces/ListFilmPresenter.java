package com.cinemacar.interfaces;

import com.cinemacar.pojo.Film;

import java.util.ArrayList;

public interface ListFilmPresenter {
	void onSuccess(ArrayList<Film> films);

	void onFail(String key);
}
