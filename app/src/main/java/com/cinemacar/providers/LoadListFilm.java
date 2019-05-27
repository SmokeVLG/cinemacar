package com.cinemacar.providers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.ListFilmLoadPresenterInterface;
import com.cinemacar.pojo.Film;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadListFilm {
	private ListFilmLoadPresenterInterface loadListFilmPresenterInterface;


	public LoadListFilm(ListFilmLoadPresenterInterface loadListFilmPresenterInterface) {
		this.loadListFilmPresenterInterface = loadListFilmPresenterInterface;
	}

	public void getFilms() {
		DatabaseReference myRef;
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		myRef = database.getReference("films");
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				GenericTypeIndicator<ArrayList<Film>> genericTypeIndicator =
						new GenericTypeIndicator<ArrayList<Film>>() {
						};
				ArrayList<Film> films = dataSnapshot.child("films").getValue(genericTypeIndicator);
				loadListFilmPresenterInterface.success(films);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				loadListFilmPresenterInterface.fail(Const.FILMS_NOT_FOUND);
				Log.e(LoadListFilm.class.getSimpleName(), "Ошибка при чтении данных из базы данных.", error.toException());
			}
		});
	}
}