package com.cinemacar.list;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.interfaces.OnListFilmItemClickListener;
import com.cinemacar.model.ListFilm;
import com.cinemacar.pojo.Film;
import com.cinemacar.pojo.Time;
import com.squareup.picasso.Picasso;

import java.util.List;

class ListFilmViewHolder extends RecyclerView.ViewHolder {
	private static final int ONE_FILM_IN_DAY = 1;
	private static final int TWO_FILM_IN_DAY = 2;
	private TextView filmName;
	private TextView secondFilmName;
	private TextView dayText;
	private CardView cv_first_film;
	private TextView firstFilmTime;
	private TextView firstFilmDescription;
	private ImageView firstFilmImage;
	private TextView secondFilmTime;
	private TextView secondFilmDescription;
	private ImageView secondFilmImage;
	private CardView cv_second_film;

	ListFilmViewHolder(@NonNull View itemView) {
		super(itemView);

		filmName = itemView.findViewById(R.id.film_name);
		secondFilmName = itemView.findViewById(R.id.second_film_name);
		dayText = itemView.findViewById(R.id.day_text);
		cv_first_film = itemView.findViewById(R.id.cv_first_film);
		firstFilmDescription = itemView.findViewById(R.id.first_film_description);
		firstFilmTime = itemView.findViewById(R.id.first_film_time);
		firstFilmImage = itemView.findViewById(R.id.first_film_image);
		secondFilmDescription = itemView.findViewById(R.id.second_film_description);
		secondFilmTime = itemView.findViewById(R.id.second_film_time);
		secondFilmImage = itemView.findViewById(R.id.second_film_image);
		cv_second_film = itemView.findViewById(R.id.cv_second_film);
	}

	@SuppressLint("SetTextI18n")
	void bindView(final int position, final OnListFilmItemClickListener itemClickListener) {
		final Film currentItem = ListFilm.getInstance().getFilms().get(position);
		dayText.setText(currentItem.getDate() + "\n" + currentItem.getPlace());
		List<Time> times = currentItem.getTimes();
		int countFilmsInDay = times.size();
		int numberFilm = 0;
		switch (countFilmsInDay) {
			case ONE_FILM_IN_DAY:
				numberFilm = 0;
				firstFilmDescription.setText(times.get(numberFilm).getDescription());
				firstFilmTime.setText(times.get(numberFilm).getTime());
				Picasso
						.get()
						.load(times.get(numberFilm).getPic())
						.placeholder(R.drawable.ic_car)
						.fit()
						.into(firstFilmImage);
				cv_first_film.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						itemClickListener.onListFilmItemClickListener(position, 0);
					}
				});
				break;
			case TWO_FILM_IN_DAY:
				filmName.setText(times.get(numberFilm).getName());
				cv_second_film.setVisibility(View.VISIBLE);
				firstFilmDescription.setText(times.get(numberFilm).getDescription());
				firstFilmTime.setText(times.get(numberFilm).getTime());
				Picasso
						.get()
						.load(times.get(numberFilm).getPic())
						.placeholder(R.drawable.ic_car)
						.fit()
						.into(firstFilmImage);
				final int finalNumberFilm = numberFilm;
				cv_first_film.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						itemClickListener.onListFilmItemClickListener(position, finalNumberFilm);
					}
				});

				numberFilm = 1;
				secondFilmName.setText(times.get(numberFilm).getName());
				secondFilmDescription.setText(times.get(numberFilm).getDescription());
				secondFilmTime.setText(times.get(numberFilm).getTime());
				Picasso
						.get()
						.load(times.get(numberFilm).getPic())
						.fit()
						.placeholder(R.drawable.ic_car)
						.into(secondFilmImage);
				final int finalNumberFilm1 = numberFilm;
				cv_second_film.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						itemClickListener.onListFilmItemClickListener(position, finalNumberFilm1);
					}
				});
				break;
			default:
				break;
		}

	}

}
