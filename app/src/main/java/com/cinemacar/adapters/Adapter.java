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
import com.cinemacar.pojo.Film;
import com.cinemacar.pojo.Time;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.FilterViewHolder> {
	private List<Film> films = new ArrayList<>();

	class FilterViewHolder extends RecyclerView.ViewHolder {
		RelativeLayout rV;
		//TextView numberRaw;
		TextView placeName;
		TextView firstFilmTime;
		TextView firstFilmDescription;
		ImageView firstFilmImage;

		TextView secondFilmTime;
		TextView secondFilmDescription;
		ImageView secondFilmImage;

		LinearLayout ll_second_film;

		FilterViewHolder(View itemView) {
			super(itemView);
			rV = itemView.findViewById(R.id.r_l);
			//numberRaw = itemView.findViewById(R.id.number_raw);
			placeName = itemView.findViewById(R.id.film_date);
			firstFilmDescription = itemView.findViewById(R.id.first_film_description);
			firstFilmTime = itemView.findViewById(R.id.first_film_time);
			firstFilmImage = itemView.findViewById(R.id.first_film_image);

			secondFilmDescription = itemView.findViewById(R.id.second_film_description);
			secondFilmTime = itemView.findViewById(R.id.second_film_time);
			secondFilmImage = itemView.findViewById(R.id.second_film_image);

			ll_second_film = itemView.findViewById(R.id.ll_second_film);
		}
	}

	public Adapter(List<Film> films) {
		this.films = films;
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
		final Film currentItem = films.get(position);

		String number = (position + 1) + ".";
		//holder.numberRaw.setText(number);
		holder.placeName.setText(currentItem.getDate());

		List<Time> times = currentItem.getTimes();

		if (times.size() == 1) {
			holder.firstFilmDescription.setText(times.get(0).getDescription());
			holder.firstFilmTime.setText(times.get(0).getTime());
			Picasso
					.get()
					.load(times.get(0).getPic())
					.fit()
					.into(holder.firstFilmImage);
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


			holder.secondFilmDescription.setText(times.get(1).getDescription());
			holder.secondFilmTime.setText(times.get(1).getTime());
			Picasso
					.get()
					.load(times.get(1).getPic())
					.fit()
					.into(holder.secondFilmImage);



		}
		//for (Time curTime:times) {

		//}

		/*holder.firstFilmDescription.setText(currentItem.getDescription());
		Picasso
				.get()
				.load(currentItem.getPic())
				.fit()
				.into(holder.firstFilmImage);*/

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