package com.cinemacar.presenters;

import android.util.Log;

import com.cinemacar.App;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IListDayPresenter;
import com.cinemacar.interfaces.IListDayView;
import com.cinemacar.list.ListDayAdapter;
import com.cinemacar.model.ListDay;
import com.cinemacar.pojo.Day;
import com.cinemacar.repositories.LoadListDays;

import java.util.ArrayList;

public class LoadListDayPresenter implements IListDayPresenter {
	private static String TAG = LoadListDayPresenter.class.getSimpleName();
	private IListDayView iListDayView;
	private LoadListDays loadListDays;

	public LoadListDayPresenter(IListDayView iListDayView) {
		this.iListDayView = iListDayView;
		loadListDays = new LoadListDays(this);
	}

	public void loadFilms() {
		Log.d(TAG, "Получить список фильмов.");
		iListDayView.showLoadingFilms();
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
			ListDay.getInstance().setDays(days);
			ListDayAdapter listDayAdapter = new ListDayAdapter(days, this);
			iListDayView.showSuccessLoadFilms(listDayAdapter);
		} else {
			iListDayView.showFailLoadFilms(Const.FILMS_NOT_FOUND);
		}
	}

	@Override
	public void setFailLoadFilms(String key) {
		Log.d(TAG, "Ошибка при загрузке списка фильмов.");
		iListDayView.showFailLoadFilms(Const.FILMS_NOT_FOUND);
	}

	@Override
	public void onFilmClick(String link) {
		Log.d(TAG, "Переход на страницу кинопоиска.");
		iListDayView.goToFragment(WebViewFragment.newInstance(link));
	}
}
