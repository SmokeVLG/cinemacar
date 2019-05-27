package com.cinemacar.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.cinemacar.adapters.Adapter;
import com.cinemacar.helpers.HelperConstants;
import com.cinemacar.interfaces.FilmsListInterface;
import com.cinemacar.interfaces.OnFilmClickListener;
import com.cinemacar.model.FilmList;
import com.cinemacar.pojo.Film;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A fragment that launches other parts of the demo application.
 */
public class ListFilmFragment extends Fragment implements FilmsListInterface {
	private SwipeRefreshLayout refresh;
	private LinearLayout infoLayout;
	private TextView infoMessage;
	private ImageView previewInfo;
	private Adapter adapter;
	private RecyclerView mRecyclerView;
	private String TAG = ListFilmFragment.class.getSimpleName();
	private DatabaseReference myRef;
	private OnFilmClickListener listener;

	@Override
	public void onAttach(Context context) {
		if (context instanceof OnFilmClickListener) {
			listener = (OnFilmClickListener) context;
		}
		((MainActivity) getActivity()).isShowToolBar(View.VISIBLE);
		super.onAttach(context);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		myRef = database.getReference("films");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list_fragment, container, false);
		return initGUI(rootView);
	}

	private View initGUI(View root) {
		((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));
		refresh = root.findViewById(R.id.refresh);
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				getFilms();
			}
		});
		mRecyclerView = root.findViewById(R.id.films_recycler);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);
		infoLayout = root.findViewById(R.id.info);
		infoMessage = root.findViewById(R.id.info_message);
		previewInfo = root.findViewById(R.id.preview_info);
		getFilms();
		return root;
	}

	private void getFilms() {
		loading();
		if (isOnline()) {
			myRef.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
					GenericTypeIndicator<ArrayList<Film>> genericTypeIndicator =
							new GenericTypeIndicator<ArrayList<Film>>() {
							};
					ArrayList<Film> films = dataSnapshot.child("films").getValue(genericTypeIndicator);
					if (films.size() > 0) {
						FilmList.getInstance().setFilms(films);
						adapter = new Adapter(films, listener);
						success(adapter);
					} else {
						fail(HelperConstants.FILMS_NOT_FOUND);
					}
				}

				@Override
				public void onCancelled(@NonNull DatabaseError error) {
					fail(HelperConstants.FILMS_NOT_FOUND);
					Log.e(TAG, "Ошибка при чтении данных из базы данных.", error.toException());
				}
			});
		} else {
			fail(HelperConstants.INTERNET_NOT_FOUND);
		}
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

	@Override
	public void success(Adapter adapter) {
		refresh.setRefreshing(false);
		infoLayout.setVisibility(View.GONE);
		mRecyclerView.setAdapter(adapter);
		mRecyclerView.setVisibility(View.VISIBLE);
		refresh.setRefreshing(false);
	}

	@Override
	public void fail(String keyError) {
		refresh.setRefreshing(false);
		mRecyclerView.setVisibility(View.GONE);
		infoMessage.setText(HelperConstants.getMessage(keyError));
		previewInfo.setImageDrawable(getActivity().getResources().getDrawable(HelperConstants.getPreview(keyError)));
		infoLayout.setVisibility(View.VISIBLE);
		refresh.setRefreshing(false);
	}

	private boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

}