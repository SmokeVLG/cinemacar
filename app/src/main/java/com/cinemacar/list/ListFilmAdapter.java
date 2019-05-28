package com.cinemacar.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinemacar.R;
import com.cinemacar.interfaces.IListFilmPresenter;
import com.cinemacar.model.ListDays;
import com.cinemacar.pojo.Day;

import java.util.List;

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmViewHolder> {
	private List<Day> days;
	private IListFilmPresenter iListFilmPresenter;

	public ListFilmAdapter(List<Day> days, IListFilmPresenter iListFilmPresenter) {
		this.days = days;
		this.iListFilmPresenter = iListFilmPresenter;
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
		holder.bindView(ListDays.getInstance().getDays().get(position), iListFilmPresenter);
	}

	@Override
	public int getItemCount() {
		if (days != null) {
			return days.size();
		} else {
			return 0;
		}
	}

}