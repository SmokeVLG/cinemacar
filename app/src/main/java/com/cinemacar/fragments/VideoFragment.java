package com.cinemacar.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.cinemacar.R;
import com.cinemacar.activities.MainActivity;

public class VideoFragment extends Fragment {

	public static final String VIDEO_URL = "videoURL";
	VideoView videoView;
	ProgressBar progressBar;
	private View view;

	public VideoFragment() {
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_video, container, false);
		videoView = view.findViewById(R.id.videoView);
		progressBar = view.findViewById(R.id.progressbar);
		((AppCompatActivity) getActivity()).getSupportActionBar().hide();
		initVideoView();
		return view;

	}

	private void initVideoView() {
		String url = getArguments().getString(VIDEO_URL);
		if (videoView != null) {
			progressBar.setVisibility(View.VISIBLE);
			videoView.setVideoURI(Uri.parse(url));
			videoView.seekTo(1000);
			progressBar.setVisibility(View.VISIBLE);
			videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					//Get your video's width and height
					int videoWidth = mp.getVideoWidth();
					int videoHeight = mp.getVideoHeight();

					//Get VideoView's current width and height
					int videoViewWidth = videoView.getWidth();
					int videoViewHeight = videoView.getHeight();

					float xScale = (float) videoViewWidth / videoWidth;
					float yScale = (float) videoViewHeight / videoHeight;

					//For Center Crop use the Math.max to calculate the scale
					//float scale = Math.max(xScale, yScale);
					//For Center Inside use the Math.min scale.
					//I prefer Center Inside so I am using Math.min
					float scale = Math.min(xScale, yScale);

					float scaledWidth = scale * videoWidth;
					float scaledHeight = scale * videoHeight;

					//Set the new size for the VideoView based on the dimensions of the video
					ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
					layoutParams.width = (int)scaledWidth;
					layoutParams.height = (int)scaledHeight;
					videoView.setLayoutParams(layoutParams);

					/*Display display = getActivity().getWindowManager().getDefaultDisplay();
					int width = mp.getVideoWidth();
					int height = mp.getVideoHeight();
					videoView.setLayoutParams(new RelativeLayout.LayoutParams(width,height));*/

					//videoView.getLayoutParams().width = mp.getVideoWidth();
					//videoView.getLayoutParams().height = mp.getVideoHeight();

					//videoView.setLayoutParams(new FrameLayout.LayoutParams(mp.getVideoWidth(),mp.getVideoHeight()));
					//videoView.requestLayout();

					mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
						@Override
						public boolean onInfo(MediaPlayer mp, int what, int extra) {
							Log.e(VideoFragment.class.getSimpleName(), "what " + what);
							if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START || what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
								Log.e(VideoFragment.class.getSimpleName(), "start");
								progressBar.setVisibility(View.GONE);
								videoView.start();
								return true;
							}
							Log.e(VideoFragment.class.getSimpleName(), "false");
							return false;
						}
					});
					mp.setLooping(true);
				}
			});


		}
	}

	@Override
	public void onAttach(Context context) {
		((MainActivity) getActivity()).isShowToolBar(View.GONE);
		super.onAttach(context);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}
