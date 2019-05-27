package com.cinemacar.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.cinemacar.R;
import com.cinemacar.fragments.ListFilmFragment;


public class MainActivity extends AppCompatActivity {
	public static String TAG = MainActivity.class.getSimpleName();

	ListFilmFragment listFilmFragment;
	FragmentManager fragmentManager;
	Toolbar toolBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolBar = findViewById(R.id.tool_bar);
		setSupportActionBar(toolBar);
		initFragments();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "Переход во фрагмент со списком фильмов.");
		goToFragment(listFilmFragment);
	}

	public void goToFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.container, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	private void initFragments() {
		fragmentManager = getSupportFragmentManager();
		listFilmFragment = new ListFilmFragment();
	}

	@Override
	public void onBackPressed() {
		int stackCount = fragmentManager.getBackStackEntryCount();
		if (stackCount == 1) finish();
		else super.onBackPressed();
	}

}
