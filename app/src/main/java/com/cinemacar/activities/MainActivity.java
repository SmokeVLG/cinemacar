package com.cinemacar.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.cinemacar.R;
import com.cinemacar.fragments.ListDaysFragment;


public class MainActivity extends AppCompatActivity {
	public static String TAG = MainActivity.class.getSimpleName();

	ListDaysFragment listDaysFragment;
	FragmentManager fragmentManager;
	Toolbar toolBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initGUI();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "Переход во фрагмент со списком фильмов.");
		goToFragment(listDaysFragment);
	}

	public void goToFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.container, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	private void initGUI() {
		Log.d(TAG, "Инициализация GUI основной активности.");
		toolBar = findViewById(R.id.tool_bar);
		setSupportActionBar(toolBar);
		fragmentManager = getSupportFragmentManager();
		listDaysFragment = new ListDaysFragment();
	}

	@Override
	public void onBackPressed() {
		int stackCount = fragmentManager.getBackStackEntryCount();
		if (stackCount == 1) finish();
		else super.onBackPressed();
	}

}
