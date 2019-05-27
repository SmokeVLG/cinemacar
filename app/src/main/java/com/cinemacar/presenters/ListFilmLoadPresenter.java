package com.cinemacar.presenters;

import com.cinemacar.App;
import com.cinemacar.adapters.ListFilmAdapter;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.ListFilmLoadInterface;
import com.cinemacar.interfaces.ListFilmLoadPresenterInterface;
import com.cinemacar.interfaces.OnListFilmItemClickListener;
import com.cinemacar.model.ListFilm;
import com.cinemacar.pojo.Film;
import com.cinemacar.providers.LoadListFilm;

import java.util.ArrayList;

public class ListFilmLoadPresenter implements ListFilmLoadPresenterInterface, OnListFilmItemClickListener {
	private ListFilmLoadInterface listFilmLoadInterface;
	private LoadListFilm loadListFilm;

	public ListFilmLoadPresenter(ListFilmLoadInterface listFilmLoadInterface) {
		this.listFilmLoadInterface = listFilmLoadInterface;
		loadListFilm = new LoadListFilm(this);
	}

	public void getFilms() {
		listFilmLoadInterface.loading();
		if (App.getInstance().isOnline()) {
			loadListFilm.getFilms();
		} else {
			fail(Const.INTERNET_NOT_FOUND);
		}
	}

	@Override
	public void success(ArrayList<Film> films) {
		if (films.size() > 0) {
			ListFilm.getInstance().setFilms(films);
			ListFilmAdapter listFilmAdapter = new ListFilmAdapter(films, this);
			listFilmLoadInterface.success(listFilmAdapter);
		} else {
			listFilmLoadInterface.fail(Const.FILMS_NOT_FOUND);
		}
	}

	@Override
	public void fail(String key) {
		listFilmLoadInterface.fail(Const.FILMS_NOT_FOUND);
	}

	@Override
	public void onListFilmItemClickListener(int index, int numberFilm) {
		listFilmLoadInterface.goToFragment(WebViewFragment.newInstance(ListFilm.getInstance().getFilms().get(index).getTimes().get(numberFilm).getLink()));
	}
}
