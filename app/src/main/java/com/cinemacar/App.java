package com.cinemacar;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(mailTo = "maxden33@gmail.com",
		mode = ReportingInteractionMode.TOAST,
		resToastText = R.string.crash_toast_text)
public class App extends Application {
	private static App ourInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		ACRA.init(this);
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
