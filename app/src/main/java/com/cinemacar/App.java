package com.cinemacar;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class App extends Application {
	private static App ourInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		if (ourInstance == null) ourInstance = this;
	}

	public static App getInstance() {
		return ourInstance;
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnected();
	}

}
