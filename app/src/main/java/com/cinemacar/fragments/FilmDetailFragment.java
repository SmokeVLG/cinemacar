package com.cinemacar.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.activities.MainActivity;
import com.cinemacar.model.FilmList;
import com.cinemacar.pojo.Film;
import com.squareup.picasso.Picasso;


public class FilmDetailFragment extends Fragment {
	private static final String FILM_INDEX = "filmIndex";
	public static int numberFilm = 0;

	public static FilmDetailFragment initFragment(int workoutIndex, int numberFilmTemp) {
		FilmDetailFragment fragment = new FilmDetailFragment();
		numberFilm = numberFilmTemp;
		Bundle arguments = new Bundle();
		arguments.putInt(FILM_INDEX, workoutIndex);
		fragment.setArguments(arguments);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_film_detail, container, false);
		Film workout = FilmList.getInstance().getFilms().get(getArguments().getInt(FILM_INDEX));
		initGUI(root, workout);
		return root;
	}

	private void initGUI(View view, final Film currentFilm) {
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
		TextView filmName = view.findViewById(R.id.film_name);
		TextView filmTime = view.findViewById(R.id.film_time);
		TextView filmDescription = view.findViewById(R.id.film_description);
		ImageView filmImage = view.findViewById(R.id.film_image);
		Picasso.get().load(currentFilm.getTimes().get(numberFilm).getPic()).fit().into(filmImage);
		((MainActivity) getActivity()).getSupportActionBar().setTitle(currentFilm.getTimes().get(numberFilm).getName());
		filmName.setText(currentFilm.getTimes().get(numberFilm).getName());
		filmDescription.setText(currentFilm.getTimes().get(numberFilm).getTime());
		filmTime.setText(currentFilm.getTimes().get(numberFilm).getDescription());
		filmImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openVideo(currentFilm);
			}
		});

	}

	private void openVideo(Film currentFilm) {
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();
		VideoFragment videoFragment = new VideoFragment();
		Bundle args = new Bundle();
		args.putString(VideoFragment.VIDEO_URL, currentFilm.getTimes().get(numberFilm).getVideo());
		videoFragment.setArguments(args);
		fragmentTransaction.replace(R.id.container, videoFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}


}