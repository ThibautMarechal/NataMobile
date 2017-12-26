package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.helmo.natamobile.R;
import be.helmo.natamobile.adapter.ObservationListViewAdapter;
import be.helmo.natamobile.presenter.implementations.CurrentSessionPresenter;
import be.helmo.natamobile.presenter.interfaces.ICurrentSessionPresenter;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionActivity extends AbstractActivity implements ICurrentSessionView {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_VIDEO_CAPTURE = 2;
    private static final int REQUEST_AUDIO_CAPTURE = 3;
    private final ICurrentSessionPresenter presenter;
    private String filePath;

    public CurrentSessionActivity() {
        this.presenter = new CurrentSessionPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_current);
        //PICTURE
        ImageButton newPicture = findViewById(R.id.observaitionPictureButton);
        newPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        //VIDEO
        ImageButton newVideo = findViewById(R.id.observaitionVideoButton);
        newVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeVideo();
            }
        });
        //AUDIO
        ImageButton newAudio = findViewById(R.id.observaitionAudioButton);
        newAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeAudio();
            }
        });
        //NOMEDIA
        ImageButton newNoMedia = findViewById(R.id.observaitionNoMediaButton);
        newNoMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.newObservationNoMedia();
            }
        });
        //OBSERVATION LIST
        ObservationListViewAdapter<String[]> adapter = new ObservationListViewAdapter<>(this, presenter.getObservations());
        ListView observationListView = findViewById(R.id.currentSessionObservationList);
        observationListView.setAdapter(adapter);

    }

    private void takeAudio() {
        Intent intent = new Intent(this, AudioRecorderActivity.class);
        startActivityForResult(intent,REQUEST_AUDIO_CAPTURE);
    }

    private void takeVideo() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = Uri.parse(photoFile.getPath());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            presenter.newObservationPicture(this.filePath);
        }else if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            Uri videoUri = intent.getData();
            if (videoUri != null) {
                this.filePath = videoUri.getPath();
                presenter.newObservationVideo(this.filePath);
            }
        }else if(requestCode == REQUEST_AUDIO_CAPTURE && resultCode == RESULT_OK){
            Uri audioUri = intent.getData();
            if (audioUri != null) {
                this.filePath = audioUri.getPath();
                presenter.newObservationAudio(this.filePath);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PIC_" + timeStamp + "_";
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        this.filePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void updateObservationList() {
        ListView observationListView = findViewById(R.id.currentSessionObservationList);
        ObservationListViewAdapter adapter = (ObservationListViewAdapter) observationListView.getAdapter();
    }
}
