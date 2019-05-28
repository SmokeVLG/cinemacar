package com.cinemacar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.activities.MainActivity;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IListDayView;
import com.cinemacar.list.ListDayAdapter;
import com.cinemacar.presenters.LoadListDayPresenter;

/**
 * A fragment that launches other parts of the demo application.
 */
public class ListDaysFragment extends Fragment implements IListDayView {
	public static String TAG = ListDaysFragment.class.getSimpleName();
	private SwipeRefreshLayout refresh;
	private LinearLayout infoLayout;
	private TextView infoMessage;
	private ImageView previewInfo;
	private RecyclerView mRecyclerView;
	LoadListDayPresenter loadListFilmPresenter;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		loadListFilmPresenter = new LoadListDayPresenter(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.film_list_fragment, container, false);
		Log.d(TAG, "Инициализация интерфейса со списком фильмов.");
		return initGUI(rootView);
	}

	private View initGUI(View root) {
		((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));
		refresh = root.findViewById(R.id.refresh);
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				loadListFilmPresenter.loadFilms();
			}
		});
		mRecyclerView = root.findViewById(R.id.films_recycler);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);
		infoLayout = root.findViewById(R.id.info);
		infoMessage = root.findViewById(R.id.info_message);
		previewInfo = root.findViewById(R.id.preview_info);
		//Сообщаем презентеру о необходимости загрузки фильмов
		loadListFilmPresenter.loadFilms();
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	/*
	 * Показать загруженные фильмы
	 * */
	@Override
	public void showLoadingFilms() {
		Log.d(TAG, "Отображение загруженных фильмов.");
		refresh.setRefreshing(true);
		infoLayout.setVisibility(View.GONE);
		mRecyclerView.setVisibility(View.GONE);
	}

	/*
	 * Успешная загрузка фильмов загрузки списка фильмов
	 * */
	@Override
	public void showSuccessLoadFilms(ListDayAdapter listDayAdapter) {
		Log.d(TAG, "Список фильмов успешно загружен.");
		refresh.setRefreshing(false);
		infoLayout.setVisibility(View.GONE);
		mRecyclerView.setAdapter(listDayAdapter);
		mRecyclerView.setVisibility(View.VISIBLE);
		refresh.setRefreshing(false);
	}

	/*
	 * Ошибка загрузки списка фильмов
	 * */
	@Override
	public void showFailLoadFilms(String keyError) {
		Log.d(TAG, "Ошибка при загрузке списка фильмов успешно загружен.");
		refresh.setRefreshing(false);
		mRecyclerView.setVisibility(View.GONE);
		infoMessage.setText(Const.getMessage(keyError));
		previewInfo.setImageDrawable(getActivity().getResources().getDrawable(Const.getPreview(keyError)));
		infoLayout.setVisibility(View.VISIBLE);
		refresh.setRefreshing(false);
	}

	/*
	 *Переход на другой фрагмент.
	 * */
	@Override
	public void goToFragment(Fragment fragment) {
		Log.d(TAG, "Переход на фрагмент -->" + fragment.getClass().getSimpleName());
		FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.container, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}


}