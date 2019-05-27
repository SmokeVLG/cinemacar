package com.cinemacar.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinemacar.R;
import com.cinemacar.interfaces.IListFilmItemClick;
import com.cinemacar.pojo.Film;

import java.util.List;

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmViewHolder> {
	private List<Film> films;
	private IListFilmItemClick iListFilmItemClick;

	public ListFilmAdapter(List<Film> films, IListFilmItemClick iListFilmItemClick) {
		this.films = films;
		this.iListFilmItemClick = iListFilmItemClick;
	}

	@NonNull
	@Override
	public ListFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item,
				parent, false);
		return new ListFilmViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final ListFilmViewHolder holder, int position) {
		holder.bindView(position, iListFilmItemClick);
	}

	@Override
	public int getItemCount() {
		if (films != null) {
			return films.size();
		} else {
			return 0;
		}
	}

}