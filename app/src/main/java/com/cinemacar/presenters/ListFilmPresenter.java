package com.cinemacar.presenters;

import com.cinemacar.App;
import com.cinemacar.list.ListFilmAdapter;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.ListFilmView;
import com.cinemacar.interfaces.OnListFilmItemClickListener;
import com.cinemacar.model.ListFilm;
import com.cinemacar.pojo.Film;
import com.cinemacar.providers.LoadListFilm;

import java.util.ArrayList;

public class ListFilmPresenter implements com.cinemacar.interfaces.ListFilmPresenter, OnListFilmItemClickListener {
	private ListFilmView listFilmView;
	private LoadListFilm loadListFilm;

	public ListFilmPresenter(ListFilmView listFilmView) {
		this.listFilmView = listFilmView;
		loadListFilm = new LoadListFilm(this);
	}

	public void getFilms() {
		listFilmView.showLoading();
		if (App.getInstance().isOnline()) {
			loadListFilm.getFilms();
		} else {
			onFail(Const.INTERNET_NOT_FOUND);
		}
	}

	@Override
	public void onSuccess(ArrayList<Film> films) {
		if (films.size() > 0) {
			ListFilm.getInstance().setFilms(films);
			ListFilmAdapter listFilmAdapter = new ListFilmAdapter(films, this);
			listFilmView.setSuccess(listFilmAdapter);
		} else {
			listFilmView.setFail(Const.FILMS_NOT_FOUND);
		}
	}

	@Override
	public void onFail(String key) {
		listFilmView.setFail(Const.FILMS_NOT_FOUND);
	}

	@Override
	public void onListFilmItemClickListener(int index, int numberFilm) {
		listFilmView.goToFragment(WebViewFragment.newInstance(ListFilm.getInstance().getFilms().get(index).getTimes().get(numberFilm).getLink()));
	}
}
