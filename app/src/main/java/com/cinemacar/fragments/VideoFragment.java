package com.cinemacar.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.cinemacar.R;

public class VideoFragment extends Fragment {

	public static final String VIDEO_URL = "videoURL";
	private View view;
	VideoView videoView;

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
		initVideoView();
		return view;

	}

	private void initVideoView() {
		//String uri = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.msr_third_video;
		//String uri = "https://www.youtube.com/watch?v=EfgUMoJrWIE&t=608s";
		String uri = getArguments().getString(VIDEO_URL);
		if (videoView != null) {
			videoView.setVideoURI(Uri.parse(uri));
			videoView.seekTo(1000);
			videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
						@Override
						public boolean onInfo(MediaPlayer mp, int what, int extra) {
							Log.e(VideoFragment.class.getSimpleName(), "what " + what);
							if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START || what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
								Log.e(VideoFragment.class.getSimpleName(), "start");
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
		super.onAttach(context);

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}
