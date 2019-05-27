package com.cinemacar.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cinemacar.R;
import com.cinemacar.fragments.ListFilmFragment;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.interfaces.IListFilmItemClickPresenter;
import com.cinemacar.model.ListFilm;


public class MainActivity extends AppCompatActivity implements IListFilmItemClickPresenter {

	ListFilmFragment listFilmFragment;
	FragmentManager fragmentManager;
	Toolbar toolBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolBar = findViewById(R.id.tool_bar);
		setSupportActionBar(toolBar);
		fragmentManager = getSupportFragmentManager();
		initFragments();
	}

	@Override
	protected void onStart() {
		super.onStart();
		goToFragment(listFilmFragment);
	}

	public void goToFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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
	public void onListFilmItemClickListener(int index, int numberFilm) {
		goToFragment(WebViewFragment.newInstance(ListFilm.getInstance().getFilms().get(index).getTimes().get(numberFilm).getLink()));
	}

}
