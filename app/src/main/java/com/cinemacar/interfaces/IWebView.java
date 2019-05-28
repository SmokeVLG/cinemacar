package com.cinemacar.interfaces;

public interface IWebView {
	//Показать загрузку страничку
	void showLoadingKinopoiskLink();

	//Успешное открытие стрнички
	void showSuccessLoadKinopoiskLink();

	//Открыть страничку
	void openURL(String urlForKinopoisk);
}
