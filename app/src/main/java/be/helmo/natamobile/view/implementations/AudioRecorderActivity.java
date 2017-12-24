package be.helmo.natamobile.view.implementations;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

import be.helmo.natamobile.R;
import be.helmo.natamobile.view.interfaces.IAudioRecorderView;

/**
 * Created by marechthib on 22/12/2017.
 */

public class AudioRecorderActivity extends AbstractActivity implements IAudioRecorderView{
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private MediaRecorder mRecorder ;
    private String mFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_recorder);

        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
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
                if(!started){
                    startRecording();
                }else{
                    stopRecording();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                boolean permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!permissionToRecordAccepted ) finish();
        }
    }


    private void startRecording(){
        Button controlButton = findViewById(R.id.audioRecorderControlButton);
        controlButton.setText(R.string.stop_recording);
        ImageView recorderImage = findViewById(R.id.audioRecorderImage);
        recorderImage.setImageResource(R.drawable.mic);
       //TODO FIX AUDIO RECORDER
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        String filePath = getExternalCacheDir().getAbsolutePath() + "/myAudio.acc";
        mRecorder.setOutputFile(filePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            Log.e("Audiorecorder", "prepare() failed");
        }
    }
    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

    }
}
