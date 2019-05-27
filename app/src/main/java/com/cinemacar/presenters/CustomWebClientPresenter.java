package com.cinemacar.presenters;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cinemacar.interfaces.WebViewPageLoadInterface;


public class CustomWebClientPresenter extends WebViewClient {

	private WebViewPageLoadInterface webViewPageLoadInterface;

	public CustomWebClientPresenter(WebViewPageLoadInterface webViewPageLoadInterface) {
		this.webViewPageLoadInterface = webViewPageLoadInterface;
	}

	@Override
	public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
		super.onReceivedHttpError(view, request, errorResponse);
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		return super.shouldOverrideUrlLoading(view, url);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		webViewPageLoadInterface.loading();
		super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		webViewPageLoadInterface.successLoad();
		super.onPageFinished(view, url);
	}

	@Override
	public void onLoadResource(WebView view, String url) {
		super.onLoadResource(view, url);
	}

}
