package com.cinemacar.presenters;

import android.util.Log;

import com.cinemacar.App;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IListFilmPresenter;
import com.cinemacar.interfaces.IListFilmView;
import com.cinemacar.list.ListFilmAdapter;
import com.cinemacar.model.ListDays;
import com.cinemacar.pojo.Day;
import com.cinemacar.repositories.LoadListDays;

import java.util.ArrayList;

public class LoadListFilmPresenter implements IListFilmPresenter {
	private static String TAG = LoadListFilmPresenter.class.getSimpleName();
	private IListFilmView iListFilmView;
	private LoadListDays loadListDays;

	public LoadListFilmPresenter(IListFilmView iListFilmView) {
		this.iListFilmView = iListFilmView;
		loadListDays = new LoadListDays(this);
	}

	public void loadFilms() {
		Log.d(TAG, "Получить список фильмов.");
		iListFilmView.showLoadingFilms();
		if (App.getInstance().isOnline()) {
			loadListDays.getFilms();
		} else {
			setFailLoadFilms(Const.INTERNET_NOT_FOUND);
		}
	}

	@Override
	public void setSuccessLoadFilms(ArrayList<Day> days) {
		Log.d(TAG, "Успешная загрузка списка фильмов.");
		if (days.size() > 0) {
			ListDays.getInstance().setDays(days);
			ListFilmAdapter listFilmAdapter = new ListFilmAdapter(days, this);
			iListFilmView.showSuccessLoadFilms(listFilmAdapter);
		} else {
			iListFilmView.showFailLoadFilms(Const.FILMS_NOT_FOUND);
		}
	}

	@Override
	public void setFailLoadFilms(String key) {
		Log.d(TAG, "Ошибка при загрузке списка фильмов.");
		iListFilmView.showFailLoadFilms(Const.FILMS_NOT_FOUND);
	}

	@Override
	public void onFilmClick(String link) {
		Log.d(TAG, "Переход на страницу кинопоиска.");
		iListFilmView.goToFragment(WebViewFragment.newInstance(link));
	}
}
