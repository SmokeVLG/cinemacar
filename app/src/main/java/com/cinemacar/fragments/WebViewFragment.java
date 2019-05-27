package com.cinemacar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.cinemacar.R;
import com.cinemacar.helpers.Const;
import com.cinemacar.interfaces.IWebView;
import com.cinemacar.presenters.WebViewPresenter;


public class WebViewFragment extends Fragment implements IWebView {

	private WebView webView;
	private ProgressBar loading;
	private String urlForKinopoisk;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.web_view_fragment, container, false);
		initGUI(root);
		return root;
	}

	private void initGUI(View root) {
		webView = root.findViewById(R.id.web_view);
		loading = root.findViewById(R.id.loading_progress);
		webView.setWebViewClient(new WebViewPresenter(this));
		openURL(urlForKinopoisk);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void openURL(String url) {
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
	public void showLoading() {
		webView.setVisibility(View.GONE);
		loading.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSuccessLoad() {
		webView.setVisibility(View.VISIBLE);
		loading.setVisibility(View.GONE);
	}

}