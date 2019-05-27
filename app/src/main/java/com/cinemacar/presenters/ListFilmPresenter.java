package com.cinemacar.presenters;

import com.cinemacar.App;
import com.cinemacar.interfaces.IListFilmPresenter;
import com.cinemacar.list.ListFilmAdapter;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IListFilmView;
import com.cinemacar.interfaces.IListFilmItemClickPresenter;
import com.cinemacar.model.ListFilm;
import com.cinemacar.pojo.Film;
import com.cinemacar.providers.LoadListFilm;

import java.util.ArrayList;

public class ListFilmPresenter implements IListFilmPresenter, IListFilmItemClickPresenter {
	private IListFilmView IListFilmView;
	private LoadListFilm loadListFilm;

	public ListFilmPresenter(IListFilmView IListFilmView) {
		this.IListFilmView = IListFilmView;
		loadListFilm = new LoadListFilm(this);
	}

	public void getFilms() {
		IListFilmView.showLoading();
		if (App.getInstance().isOnline()) {
			loadListFilm.getFilms();
		} else {
			setFail(Const.INTERNET_NOT_FOUND);
		}
	}

	@Override
	public void setSuccess(ArrayList<Film> films) {
		if (films.size() > 0) {
			ListFilm.getInstance().setFilms(films);
			ListFilmAdapter listFilmAdapter = new ListFilmAdapter(films, this);
			IListFilmView.showSuccessLoad(listFilmAdapter);
		} else {
			IListFilmView.showFailLoad(Const.FILMS_NOT_FOUND);
		}
	}

	@Override
	public void setFail(String key) {
		IListFilmView.showFailLoad(Const.FILMS_NOT_FOUND);
	}

	@Override
	public void onListFilmItemClickListener(int index, int numberFilm) {
		IListFilmView.goToFragment(WebViewFragment.newInstance(ListFilm.getInstance().getFilms().get(index).getTimes().get(numberFilm).getLink()));
	}
}
