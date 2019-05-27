package com.cinemacar.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.cinemacar.R;
import com.cinemacar.fragments.ListFilmFragment;
import com.cinemacar.fragments.VideoFragment;
import com.cinemacar.fragments.WebViewFragment;
import com.cinemacar.interfaces.ActivityInterface;
import com.cinemacar.interfaces.OnFilmClickListener;
import com.cinemacar.model.FilmList;
import com.cinemacar.pojo.Film;

import static com.cinemacar.fragments.FilmDetailFragment.numberFilm;


public class MainActivity extends AppCompatActivity implements OnFilmClickListener, ActivityInterface {

	ListFilmFragment listFilmFragment;
	FragmentManager fragmentManager;
	Toolbar toolBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
		//Remove notification bar
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
	public void onListItemClickListener(int index, int numberFilm) {
		Film curFilm = FilmList.getInstance().getFilms().get(index);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//openVideo(curFilm);
		goToFragment(WebViewFragment.newInstance(curFilm.getTimes().get(numberFilm).getLink()));
	}

	@Override
	public void isShowToolBar(int visibility) {
	}

	private void openVideo(Film currentFilm) {
		VideoFragment videoFragment = new VideoFragment();
		Bundle args = new Bundle();
		args.putString(VideoFragment.VIDEO_URL, currentFilm.getTimes().get(numberFilm).getLink());
		videoFragment.setArguments(args);
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.container, videoFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
}
