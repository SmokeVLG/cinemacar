package com.cinemacar.interfaces;

import com.cinemacar.pojo.Day;

import java.util.ArrayList;

public interface IListFilmPresenter {
	//Успешная загрузка списка фильмов
	void setSuccessLoadFilms(ArrayList<Day> days);

	//Ошибка при загрузке списка фильмов
	void setFailLoadFilms(String key);

	//Нажатие на фильм
	void onFilmClick(String link);
}
