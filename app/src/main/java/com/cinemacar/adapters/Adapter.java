package com.cinemacar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.cinemacar.R;
import com.cinemacar.pojo.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.FilterViewHolder> {
	private List<Film> placeList = new ArrayList<>();

	class FilterViewHolder extends RecyclerView.ViewHolder {
		RelativeLayout rV;
		//TextView numberRaw;
		TextView placeName;
		TextView placeType;
		ImageView imageView;

		FilterViewHolder(View itemView) {
			super(itemView);
			rV = itemView.findViewById(R.id.r_l);
			//numberRaw = itemView.findViewById(R.id.number_raw);
			placeName = itemView.findViewById(R.id.film_date);
			placeType = itemView.findViewById(R.id.film_description);
			imageView = itemView.findViewById(R.id.film_image);
		}
	}

	public Adapter(List<Film> placeList) {
		this.placeList = placeList;
	}


	@NonNull
	@Override
	public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item,
				parent, false);
		return new FilterViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final FilterViewHolder holder, int position) {
		final Film currentItem = placeList.get(position);

		String number = (position + 1) + ".";
		//holder.numberRaw.setText(number);
		holder.placeName.setText(currentItem.getDate());
		holder.placeType.setText(currentItem.getDescription());
		Picasso
				.get()
				.load(currentItem.getPic())
				.fit()
				.into(holder.imageView);

	}

	@Override
	public int getItemCount() {
		if (placeList != null) {
			return placeList.size();
		} else {
			return 0;
		}
	}

}