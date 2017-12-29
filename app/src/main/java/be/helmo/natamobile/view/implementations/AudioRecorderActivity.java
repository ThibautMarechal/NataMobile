package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.helmo.natamobile.R;
import be.helmo.natamobile.view.interfaces.IAudioRecorderView;

/**
 * Created by marechthib on 22/12/2017.
 */

public class AudioRecorderActivity extends AbstractActivity implements IAudioRecorderView {
	private MediaRecorder mRecorder;
	private Uri photoURI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio_recorder);
		ImageView recorderImage = findViewById(R.id.audioRecorderImage);
		recorderImage.setImageResource(R.drawable.mic_off);
		Button controlButton = findViewById(R.id.audioRecorderControlButton);
		controlButton.setOnClickListener(new View.OnClickListener() {
			private boolean started = false;

			@Override
			public void onClick(View v) {
				controleButtonClick();
			}

			private void controleButtonClick() {
				if (!started) {
					try {
						startRecording();
						started = true;
					} catch (IOException e) {
						e.printStackTrace();
						finish();
					}
				} else {
					stopRecording();
				}
			}
		});
	}


	private void startRecording() throws IOException {
		Button controlButton = findViewById(R.id.audioRecorderControlButton);
		controlButton.setText(R.string.stop_recording);
		ImageView recorderImage = findViewById(R.id.audioRecorderImage);
		recorderImage.setImageResource(R.drawable.mic);
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String audioFileName = "AUD_" + timeStamp + "_";
		File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File audioFile = File.createTempFile(
			  audioFileName,  /* prefix */
			  ".3gp",         /* suffix */
			  storageDir      /* directory */
		);
		photoURI = FileProvider.getUriForFile(this,
			  "be.helmo.android.fileprovider",
			  audioFile);
		mRecorder.setOutputFile(photoURI.getPath());
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mRecorder.prepare();
		mRecorder.start();
	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
		Intent data = new Intent();
		data.setData(photoURI);
		setResult(RESULT_OK, data);
		finish();
	}
}
