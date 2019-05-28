package com.cinemacar.interfaces;

import com.cinemacar.pojo.Day;

import java.util.ArrayList;

public interface IListDayPresenter {
	//Успешная загрузка списка фильмов
	void setSuccessLoadFilms(ArrayList<Day> days);

	//Ошибка при загрузке списка фильмов
	void setFailLoadFilms(String key);

	//Нажатие на карточку фильма
	void onFilmClick(String link);
}
