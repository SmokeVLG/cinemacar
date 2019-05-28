package com.cinemacar.list;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.interfaces.IListDayPresenter;
import com.cinemacar.pojo.Day;
import com.cinemacar.pojo.Film;
import com.squareup.picasso.Picasso;

import java.util.List;

class ListDayViewHolder extends RecyclerView.ViewHolder {
	private static final int ONE_FILM_IN_DAY = 1;
	private static final int TWO_FILM_IN_DAY = 2;
	private static final int INDEX_FIRST_FILM = 0;
	private static final int INDEX_SECOND_FILM = 1;

	private TextView firstFilmName;
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

	ListDayViewHolder(@NonNull View itemView) {
		super(itemView);

		firstFilmName = itemView.findViewById(R.id.first_film_name);
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
	void bindView(final Day currentDay, final IListDayPresenter iListDayPresenter) {
		dayText.setText(currentDay.getDate() + "\n" + currentDay.getPlace());
		List<Film> films = currentDay.getFilms();
		int countFilmsInDay = films.size();
		switch (countFilmsInDay) {
			case ONE_FILM_IN_DAY:
				loadFirstFilm(films, iListDayPresenter);
				break;
			case TWO_FILM_IN_DAY:
				loadFirstFilm(films, iListDayPresenter);
				loadSecondFilm(films, iListDayPresenter);
				break;
			default:
				break;
		}

	}

	private void loadSecondFilm(final List<Film> films, final IListDayPresenter iListDayPresenter) {
		cvSecondFilm.setVisibility(View.VISIBLE);
		secondFilmName.setText(films.get(INDEX_SECOND_FILM).getName());
		secondFilmDescription.setText(films.get(INDEX_SECOND_FILM).getDescription());
		secondFilmTime.setText(films.get(INDEX_SECOND_FILM).getTime());
		Picasso
				.get()
				.load(films.get(INDEX_SECOND_FILM).getPic())
				.fit()
				.placeholder(R.drawable.ic_car)
				.into(secondFilmImage);
		cvSecondFilm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Сообщаем презентеру о нажатии на карточку фильма
				iListDayPresenter.onFilmClick(films.get(INDEX_SECOND_FILM).getLink());
			}
		});
	}

	private void loadFirstFilm(final List<Film> films, final IListDayPresenter iListDayPresenter) {
		firstFilmName.setText(films.get(INDEX_FIRST_FILM).getName());
		firstFilmDescription.setText(films.get(INDEX_FIRST_FILM).getDescription());
		firstFilmTime.setText(films.get(INDEX_FIRST_FILM).getTime());
		Picasso
				.get()
				.load(films.get(INDEX_FIRST_FILM).getPic())
				.placeholder(R.drawable.ic_car)
				.fit()
				.into(firstFilmImage);
		cvFirstFilm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Сообщаем презентеру о нажатии на карточку фильма
				iListDayPresenter.onFilmClick(films.get(INDEX_FIRST_FILM).getLink());
			}
		});
	}

}
