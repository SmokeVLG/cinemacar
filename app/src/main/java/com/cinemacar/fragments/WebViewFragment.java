package com.cinemacar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cinemacar.R;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IWebView;
import com.cinemacar.presenters.WebViewPresenter;

import static com.cinemacar.helpers.Const.INTERNET_NOT_FOUND;


public class WebViewFragment extends Fragment implements IWebView {

	public static String TAG = WebViewFragment.class.getSimpleName();
	private WebView webView;
	private ProgressBar loading;
	private String urlForKinopoisk;
	private LinearLayout infoLayout;
	private ImageView previewInfo;
	private TextView infoMessage;

	public WebViewFragment() {
	}

	public static WebViewFragment newInstance(String url) {
		WebViewFragment fragment = new WebViewFragment();
		Bundle args = new Bundle();
		args.putString(Const.URL, url);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			urlForKinopoisk = getArguments().getString(Const.URL, null);
		}
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.web_view_fragment, container, false);
		initGUI(root);
		return root;
	}

	private void initGUI(View root) {
		webView = root.findViewById(R.id.web_view);
		loading = root.findViewById(R.id.loading_progress);
		previewInfo = root.findViewById(R.id.preview_info);
		infoLayout = root.findViewById(R.id.info);
		infoMessage = root.findViewById(R.id.info_message);
		WebViewPresenter webViewPresenter = new WebViewPresenter(this);
		webView.setWebViewClient(webViewPresenter);
		//Начинаем загрузку страницы
		webViewPresenter.openURL(urlForKinopoisk);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void openURL(String url) {
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.loadUrl(url);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void showLoadingKinopoiskLink() {
		webView.setVisibility(View.GONE);
		loading.setVisibility(View.VISIBLE);
	}

	/*
	 * Убрать ролик загрузки
	 * */
	@Override
	public void showSuccessLoadKinopoiskLink() {
		webView.setVisibility(View.VISIBLE);
		loading.setVisibility(View.GONE);
	}

}