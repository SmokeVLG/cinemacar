package com.cinemacar.list;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.interfaces.IListFilmItemClick;
import com.cinemacar.model.ListFilm;
import com.cinemacar.pojo.Film;
import com.cinemacar.pojo.Time;
import com.squareup.picasso.Picasso;

import java.util.List;

class ListFilmViewHolder extends RecyclerView.ViewHolder {
	private static final int ONE_FILM_IN_DAY = 1;
	private static final int TWO_FILM_IN_DAY = 2;
	private static final int INDEX_FIRST_FILM = 0;
	private static final int INDEX_SECOND_FILM = 1;

	private TextView filmName;
	private TextView secondFilmName;
	private TextView dayText;
	private CardView cvFirstFilm;
	private TextView firstFilmTime;
	private TextView firstFilmDescription;
	private ImageView firstFilmImage;
	private TextView secondFilmTime;
	private TextView secondFilmDescription;
	private ImageView secondFilmImage;
	private CardView cvSecondFilm;

	ListFilmViewHolder(@NonNull View itemView) {
		super(itemView);

		filmName = itemView.findViewById(R.id.film_name);
		secondFilmName = itemView.findViewById(R.id.second_film_name);
		dayText = itemView.findViewById(R.id.day_text);
		cvFirstFilm = itemView.findViewById(R.id.cv_first_film);
		firstFilmDescription = itemView.findViewById(R.id.first_film_description);
		firstFilmTime = itemView.findViewById(R.id.first_film_time);
		firstFilmImage = itemView.findViewById(R.id.first_film_image);
		secondFilmDescription = itemView.findViewById(R.id.second_film_description);
		secondFilmTime = itemView.findViewById(R.id.second_film_time);
		secondFilmImage = itemView.findViewById(R.id.second_film_image);
		cvSecondFilm = itemView.findViewById(R.id.cv_second_film);
	}

	@SuppressLint("SetTextI18n")
	void bindView(final int position, final IListFilmItemClick iListFilmItemClick) {
		final Film currentItem = ListFilm.getInstance().getFilms().get(position);
		dayText.setText(currentItem.getDate() + "\n" + currentItem.getPlace());
		List<Time> times = currentItem.getTimes();
		int countFilmsInDay = times.size();
		switch (countFilmsInDay) {
			case ONE_FILM_IN_DAY:
				firstFilmDescription.setText(times.get(INDEX_FIRST_FILM).getDescription());
				firstFilmTime.setText(times.get(INDEX_FIRST_FILM).getTime());
				Picasso
						.get()
						.load(times.get(INDEX_FIRST_FILM).getPic())
						.placeholder(R.drawable.ic_car)
						.fit()
						.into(firstFilmImage);
				cvFirstFilm.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						iListFilmItemClick.onListFilmItemClickListener(position, INDEX_FIRST_FILM);
					}
				});
				break;
			case TWO_FILM_IN_DAY:
				filmName.setText(times.get(INDEX_FIRST_FILM).getName());
				cvSecondFilm.setVisibility(View.VISIBLE);
				firstFilmDescription.setText(times.get(INDEX_FIRST_FILM).getDescription());
				firstFilmTime.setText(times.get(INDEX_FIRST_FILM).getTime());
				Picasso
						.get()
						.load(times.get(INDEX_FIRST_FILM).getPic())
						.placeholder(R.drawable.ic_car)
						.fit()
						.into(firstFilmImage);

				cvFirstFilm.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						iListFilmItemClick.onListFilmItemClickListener(position, INDEX_FIRST_FILM);
					}
				});


				secondFilmName.setText(times.get(INDEX_SECOND_FILM).getName());
				secondFilmDescription.setText(times.get(INDEX_SECOND_FILM).getDescription());
				secondFilmTime.setText(times.get(INDEX_SECOND_FILM).getTime());
				Picasso
						.get()
						.load(times.get(INDEX_SECOND_FILM).getPic())
						.fit()
						.placeholder(R.drawable.ic_car)
						.into(secondFilmImage);
				cvSecondFilm.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						iListFilmItemClick.onListFilmItemClickListener(position, INDEX_SECOND_FILM);
					}
				});
				break;
			default:
				break;
		}

	}

}
