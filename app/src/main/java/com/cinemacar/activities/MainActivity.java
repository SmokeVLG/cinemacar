package com.cinemacar.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.cinemacar.R;
import com.cinemacar.fragments.FilmDetailFragment;
import com.cinemacar.fragments.ListFilmFragment;
import com.cinemacar.interfaces.OnFilmClickListener;


public class MainActivity extends AppCompatActivity implements OnFilmClickListener {

	ListFilmFragment listFilmFragment;
	FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
		setContentView(R.layout.activity_main);
		fragmentManager = getSupportFragmentManager();
		initFragments();
	}

	@Override
	protected void onStart() {
		super.onStart();
		goToFragment(listFilmFragment);
	}

	public void goToFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.container, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	private void initFragments() {
		listFilmFragment = new ListFilmFragment();
	}

	@Override
	public void onBackPressed() {
		int stackCount = fragmentManager.getBackStackEntryCount();
		if (stackCount == 1) finish();
		else super.onBackPressed();
	}

	@Override
	public void onListItemClickListener(int index,int numberFilm) {
		FilmDetailFragment detailFragment = FilmDetailFragment.initFragment(index,numberFilm);
		fragmentManager.beginTransaction().replace(R.id.container, detailFragment).addToBackStack(null).commit();

	}
}
