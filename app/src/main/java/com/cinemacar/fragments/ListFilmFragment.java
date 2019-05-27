package com.cinemacar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.activities.MainActivity;
import com.cinemacar.adapters.ListFilmAdapter;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.ListFilmLoadInterface;
import com.cinemacar.presenters.ListFilmLoadPresenter;

/**
 * A fragment that launches other parts of the demo application.
 */
public class ListFilmFragment extends Fragment implements ListFilmLoadInterface {
	private SwipeRefreshLayout refresh;
	private LinearLayout infoLayout;
	private TextView infoMessage;
	private ImageView previewInfo;
	private RecyclerView mRecyclerView;
	ListFilmLoadPresenter loadListFilmPresenter;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		loadListFilmPresenter = new ListFilmLoadPresenter(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.film_list_fragment, container, false);
		return initGUI(rootView);
	}

	private View initGUI(View root) {
		((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));
		refresh = root.findViewById(R.id.refresh);
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				loadListFilmPresenter.getFilms();
			}
		});
		mRecyclerView = root.findViewById(R.id.films_recycler);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);
		infoLayout = root.findViewById(R.id.info);
		infoMessage = root.findViewById(R.id.info_message);
		previewInfo = root.findViewById(R.id.preview_info);
		loadListFilmPresenter.getFilms();
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void loading() {
		refresh.setRefreshing(true);
		infoLayout.setVisibility(View.GONE);
		mRecyclerView.setVisibility(View.GONE);
	}

	/*
	 * Успех загрузки списка фильмов
	 * */
	@Override
	public void success(ListFilmAdapter listFilmAdapter) {
		refresh.setRefreshing(false);
		infoLayout.setVisibility(View.GONE);
		mRecyclerView.setAdapter(listFilmAdapter);
		mRecyclerView.setVisibility(View.VISIBLE);
		refresh.setRefreshing(false);
	}

	/*
	 * Ошибка загрузки списка фильмов
	 * */
	@Override
	public void fail(String keyError) {
		refresh.setRefreshing(false);
		mRecyclerView.setVisibility(View.GONE);
		infoMessage.setText(Const.getMessage(keyError));
		previewInfo.setImageDrawable(getActivity().getResources().getDrawable(Const.getPreview(keyError)));
		infoLayout.setVisibility(View.VISIBLE);
		refresh.setRefreshing(false);
	}

	@Override
	public void goToFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.container, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}


}