package com.cinemacar.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinemacar.R;
import com.cinemacar.interfaces.IListDayPresenter;
import com.cinemacar.model.ListDay;
import com.cinemacar.pojo.Day;

import java.util.List;

public class ListDayAdapter extends RecyclerView.Adapter<ListDayViewHolder> {
	private List<Day> days;
	private IListDayPresenter iListDayPresenter;

	public ListDayAdapter(List<Day> days, IListDayPresenter iListDayPresenter) {
		this.days = days;
		this.iListDayPresenter = iListDayPresenter;
	}

	@NonNull
	@Override
	public ListDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item,
				parent, false);
		return new ListDayViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final ListDayViewHolder holder, int position) {
		holder.bindView(ListDay.getInstance().getDays().get(position), iListDayPresenter);
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