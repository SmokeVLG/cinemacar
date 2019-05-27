package com.cinemacar.presenters;

import android.util.Log;

import com.cinemacar.App;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IListFilmItemClick;
import com.cinemacar.interfaces.IListFilmPresenter;
import com.cinemacar.interfaces.IListFilmView;
import com.cinemacar.list.ListFilmAdapter;
import com.cinemacar.pojo.Film;
import com.cinemacar.repositories.LoadListFilm;

import java.util.ArrayList;

public class ListFilm implements IListFilmPresenter, IListFilmItemClick {
	private static String TAG = ListFilm.class.getSimpleName();
	private IListFilmView iListFilmView;
	private LoadListFilm loadListFilm;

	public ListFilm(IListFilmView iListFilmView) {
		this.iListFilmView = iListFilmView;
		loadListFilm = new LoadListFilm(this);
	}

	public void getFilms() {
		Log.d(TAG, "Получить список фильмов.");
		iListFilmView.showLoadingFilms();
		if (App.getInstance().isOnline()) {
			loadListFilm.getFilms();
		} else {
			setFailLoadFilms(Const.INTERNET_NOT_FOUND);
		}
	}

	@Override
	public void setSuccessLoadFilms(ArrayList<Film> films) {
		Log.d(TAG, "Успешная загрузка списка фильмов.");
		if (films.size() > 0) {
			com.cinemacar.model.ListFilm.getInstance().setFilms(films);
			ListFilmAdapter listFilmAdapter = new ListFilmAdapter(films, this);
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
	public void onListFilmItemClickListener(int index, int numberFilm) {
		Log.d(TAG, "Переход на страницу кинопоиска.");
		iListFilmView.goToFragment(WebViewFragment.newInstance(com.cinemacar.model.ListFilm.getInstance().getFilms().get(index).getTimes().get(numberFilm).getLink()));
	}
}
