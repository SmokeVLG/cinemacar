package com.cinemacar.interfaces;

import android.support.v4.app.Fragment;

import com.cinemacar.list.ListFilmAdapter;

public interface ListFilmView {
	//Загрузка списка фильмов
	void showLoading();

	//Успешная загрузка списка фильмов
	void setSuccess(ListFilmAdapter listFilmAdapter);

	//Ошибка при загрузке списка фильмов
	void setFail(String keyError);

	//Переход на другой фрагмент
	void goToFragment(Fragment fragment);

}
