package com.cinemacar.presenters;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cinemacar.interfaces.IWebView;


public class WebViewPresenter extends WebViewClient {

	private IWebView IWebView;

	public WebViewPresenter(IWebView IWebView) {
		this.IWebView = IWebView;
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
		IWebView.showLoading();
		super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		IWebView.showSuccessLoad();
		super.onPageFinished(view, url);
	}

	@Override
	public void onLoadResource(WebView view, String url) {
		super.onLoadResource(view, url);
	}

}
