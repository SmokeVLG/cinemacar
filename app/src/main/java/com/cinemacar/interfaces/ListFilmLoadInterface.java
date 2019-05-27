package com.cinemacar.interfaces;

import android.support.v4.app.Fragment;

import com.cinemacar.adapters.ListFilmAdapter;

public interface ListFilmLoadInterface {
	//Загрузка списка фильмов
	void loading();

	//Успешная загрузка списка фильмов
	void success(ListFilmAdapter listFilmAdapter);

	//Ошибка при загрузке списка фильмов
	void fail(String keyError);

	//Переход на другой фрагмент
	void goToFragment(Fragment fragment);

}
