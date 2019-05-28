package com.cinemacar.interfaces;

import android.support.v4.app.Fragment;

import com.cinemacar.list.ListDayAdapter;

public interface IListDayView {
	//Загрузка списка фильмов
	void showLoadingFilms();

	//Успешная загрузка списка фильмов
	void showSuccessLoadFilms(ListDayAdapter listDayAdapter);

	//Ошибка при загрузке списка фильмов
	void showFailLoadFilms(String keyError);

	//Переход на другой фрагмент
	void goToFragment(Fragment fragment);

}
