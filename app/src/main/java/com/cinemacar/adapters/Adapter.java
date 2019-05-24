package com.cinemacar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.interfaces.OnFilmClickListener;
import com.cinemacar.pojo.Film;
import com.cinemacar.pojo.Time;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.FilterViewHolder> {
	private List<Film> films = new ArrayList<>();
	private OnFilmClickListener itemClickListener;

	class FilterViewHolder extends RecyclerView.ViewHolder {
		RelativeLayout rV;
		TextView dayText;
		LinearLayout ll_first_film;
		TextView firstFilmTime;
		TextView firstFilmDescription;
		ImageView firstFilmImage;
		TextView firstFilmPlace;

		TextView secondFilmTime;
		TextView secondFilmDescription;
		ImageView secondFilmImage;
		TextView secondFilmPlace;

		LinearLayout ll_second_film;

		FilterViewHolder(View itemView) {
			super(itemView);
			rV = itemView.findViewById(R.id.r_l);
			dayText = itemView.findViewById(R.id.day_text);
			ll_first_film = itemView.findViewById(R.id.ll_first_film);
			firstFilmDescription = itemView.findViewById(R.id.first_film_description);
			firstFilmTime = itemView.findViewById(R.id.first_film_time);
			firstFilmImage = itemView.findViewById(R.id.first_film_image);
			firstFilmPlace = itemView.findViewById(R.id.first_film_place);


			secondFilmDescription = itemView.findViewById(R.id.second_film_description);
			secondFilmTime = itemView.findViewById(R.id.second_film_time);
			secondFilmImage = itemView.findViewById(R.id.second_film_image);
			secondFilmPlace = itemView.findViewById(R.id.second_film_place);

			ll_second_film = itemView.findViewById(R.id.ll_second_film);
		}
	}

	public Adapter(List<Film> films, OnFilmClickListener itemClickListener) {
		this.films = films;
		this.itemClickListener = itemClickListener;
	}


	@NonNull
	@Override
	public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item,
				parent, false);
		return new FilterViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final FilterViewHolder holder, final int position) {
		final Film currentItem = films.get(position);

		holder.dayText.setText(currentItem.getDate());

		List<Time> times = currentItem.getTimes();

		if (times.size() == 1) {
			holder.firstFilmDescription.setText(times.get(0).getDescription());
			holder.firstFilmTime.setText(times.get(0).getTime());
			Picasso
					.get()
					.load(times.get(0).getPic())
					.fit()
					.into(holder.firstFilmImage);

			holder.ll_first_film.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					itemClickListener.onListItemClickListener(position, 0);
				}
			});

			holder.firstFilmPlace.setText(currentItem.getPlace());

		}

		if (times.size() == 2) {
			holder.ll_second_film.setVisibility(View.VISIBLE);

			holder.firstFilmDescription.setText(times.get(0).getDescription());
			holder.firstFilmTime.setText(times.get(0).getTime());
			Picasso
					.get()
					.load(times.get(0).getPic())
					.fit()
					.into(holder.firstFilmImage);
			holder.ll_first_film.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					itemClickListener.onListItemClickListener(position, 0);
				}
			});
			holder.firstFilmPlace.setText(currentItem.getPlace());

			holder.secondFilmDescription.setText(times.get(1).getDescription());
			holder.secondFilmTime.setText(times.get(1).getTime());
			Picasso
					.get()
					.load(times.get(1).getPic())
					.fit()
					.into(holder.secondFilmImage);
			holder.ll_second_film.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					itemClickListener.onListItemClickListener(position, 1);
				}
			});
			holder.secondFilmPlace.setText(currentItem.getPlace());


		}

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