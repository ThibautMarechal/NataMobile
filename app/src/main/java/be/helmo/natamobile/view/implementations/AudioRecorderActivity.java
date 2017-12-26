package be.helmo.natamobile.view.implementations;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.helmo.natamobile.R;
import be.helmo.natamobile.view.interfaces.IAudioRecorderView;

import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by marechthib on 22/12/2017.
 */

public class AudioRecorderActivity extends AbstractActivity implements IAudioRecorderView{
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private MediaRecorder mRecorder ;
    private File audioFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_recorder);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        }
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
                    try {
                        startRecording();
                        started = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        finish();
                    }
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
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) finish();
        }
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
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        this.audioFile = File.createTempFile(
                audioFileName,  /* prefix */
                ".3gp",         /* suffix */
                storageDir      /* directory */
        );
        mRecorder.setOutputFile(audioFile.getAbsoluteFile().toString());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.prepare();
        mRecorder.start();
    }
    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        Intent data = new Intent();
        data.setData(Uri.parse(this.audioFile.getAbsolutePath()));
        setResult(RESULT_OK, data);
        finish();
    }
}
