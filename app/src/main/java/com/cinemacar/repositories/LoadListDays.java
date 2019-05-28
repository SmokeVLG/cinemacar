package com.cinemacar.repositories;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IListFilmPresenter;
import com.cinemacar.pojo.Day;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadListDays {
	private static String TAG = LoadListDays.class.getSimpleName();
	private IListFilmPresenter iListFilmPresenter;

	public LoadListDays(IListFilmPresenter iListFilmPresenter) {
		this.iListFilmPresenter = iListFilmPresenter;
	}

	public void getFilms() {
		Log.d(TAG, "Получение списка фильмов.");
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference(Const.ROOT_ELEMENT);
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				Log.d(TAG, "Изменение данных списка фильмов.");
				GenericTypeIndicator<ArrayList<Day>> genericTypeIndicator =
						new GenericTypeIndicator<ArrayList<Day>>() {
						};
				ArrayList<Day> days = dataSnapshot.child(Const.ROOT_ELEMENT).getValue(genericTypeIndicator);
				iListFilmPresenter.setSuccessLoadFilms(days);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				Log.d(TAG, "Ошибка при чтении данных из базы данных.");
				iListFilmPresenter.setFailLoadFilms(Const.FILMS_NOT_FOUND);
				Log.e(LoadListDays.class.getSimpleName(), "Ошибка при чтении данных из базы данных.", error.toException());
			}
		});
	}
}
