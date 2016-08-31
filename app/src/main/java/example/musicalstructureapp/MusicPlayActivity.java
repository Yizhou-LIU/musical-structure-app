package example.musicalstructureapp;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicPlayActivity extends Activity {

	private MediaPlayer mediaPlayer;
	public TextView songName, duration;
	private String songTitle;
	private double timeElapsed = 0, finalTime = 0;
	private int forwardTime = 2000, backwardTime = 2000;
	private Handler durationHandler = new Handler();
	private SeekBar seekbar;
	int[] myMusic = {R.raw.song1, R.raw.song2, R.raw.song3};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		Intent intent = getIntent();
		songTitle = intent.getStringExtra("songname");
		initializeViews();
	}
	
	public void initializeViews(){
		songName = (TextView) findViewById(R.id.songName);
		mediaPlayer = MediaPlayer.create(this, myMusic[MusicActivity.num]);
		finalTime = mediaPlayer.getDuration();
		duration = (TextView) findViewById(R.id.songDuration);
		seekbar = (SeekBar) findViewById(R.id.seekBar);
		songName.setText(songTitle);
		seekbar.setMax((int) finalTime);
		seekbar.setClickable(false);
	}

	public void play(View view) {
		mediaPlayer.start();
		timeElapsed = mediaPlayer.getCurrentPosition();
		seekbar.setProgress((int) timeElapsed);
		durationHandler.postDelayed(updateSeekBarTime, 100);
	}

	private Runnable updateSeekBarTime = new Runnable() {
		public void run() {
			timeElapsed = mediaPlayer.getCurrentPosition();
			seekbar.setProgress((int) timeElapsed);
			double timeRemaining = finalTime - timeElapsed;
			duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
			durationHandler.postDelayed(this, 100);
		}
	};

	public void pause(View view) {
		mediaPlayer.pause();
	}

	public void forward(View view) {
		if ((timeElapsed + forwardTime) <= finalTime) {
			timeElapsed = timeElapsed + forwardTime;
			mediaPlayer.seekTo((int) timeElapsed);
		}
	}

	public void rewind(View view) {
		if ((timeElapsed - backwardTime) > 0) {
			timeElapsed = timeElapsed - backwardTime;
			mediaPlayer.seekTo((int) timeElapsed);
		}
	}
}