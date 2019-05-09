package com.cinemacar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinemacar.R;
import com.cinemacar.adapters.Adapter;
import com.cinemacar.pojo.Film;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * A fragment that launches other parts of the demo application.
 */
public class InfoFragment extends Fragment {
	private Adapter adapter;
	private RecyclerView mRecyclerView;
	private String TAG = InfoFragment.class.getSimpleName();
	private DatabaseReference myRef;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		myRef = database.getReference("films");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_info, container, false);
		mRecyclerView = rootView.findViewById(R.id.fights_recycler);
		mRecyclerView.setHasFixedSize(true);
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				GenericTypeIndicator<List<Film>> genericTypeIndicator = new GenericTypeIndicator<List<Film>>() {
				};
				List<Film> pointList = dataSnapshot.getValue(genericTypeIndicator);
				adapter = new Adapter(pointList);
				RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
				mRecyclerView.setLayoutManager(layoutManager);
				mRecyclerView.setAdapter(adapter);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				Log.d(TAG, "Ошибка при чтении данных из базы данных.", error.toException());
			}
		});

		return rootView;
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
}