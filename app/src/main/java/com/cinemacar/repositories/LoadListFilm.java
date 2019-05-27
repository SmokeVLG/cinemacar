package com.cinemacar.repositories;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IListFilmPresenter;
import com.cinemacar.pojo.Film;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadListFilm {
	public static String TAG = LoadListFilm.class.getSimpleName();
	private IListFilmPresenter iListFilmPresenter;

	public LoadListFilm(IListFilmPresenter iListFilmPresenter) {
		this.iListFilmPresenter = iListFilmPresenter;
	}

	public void getFilms() {
		Log.d(TAG, "Получение списка фильмов.");
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("films");
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				Log.d(TAG, "Изменение данных списка фильмов.");
				GenericTypeIndicator<ArrayList<Film>> genericTypeIndicator =
						new GenericTypeIndicator<ArrayList<Film>>() {
						};
				ArrayList<Film> films = dataSnapshot.child("films").getValue(genericTypeIndicator);
				iListFilmPresenter.setSuccessLoadFilms(films);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				Log.d(TAG, "Ошибка при чтении данных из базы данных.");
				iListFilmPresenter.setFailLoadFilms(Const.FILMS_NOT_FOUND);
				Log.e(LoadListFilm.class.getSimpleName(), "Ошибка при чтении данных из базы данных.", error.toException());
			}
		});
	}
}
