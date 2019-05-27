package com.cinemacar.interfaces;

import com.cinemacar.pojo.Film;

import java.util.ArrayList;

public interface IListFilmPresenter {
	void setSuccess(ArrayList<Film> films);

	void setFail(String key);
}
