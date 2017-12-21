package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;

import be.helmo.natamobile.R;
import be.helmo.natamobile.presenter.implementations.CurrentSessionPresenter;
import be.helmo.natamobile.presenter.interfaces.ICurrentSessionPresenter;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionActivity extends AbstractActivity implements ICurrentSessionView {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_VIDEO_CAPTURE = 2;
    private final ICurrentSessionPresenter presenter;

    public CurrentSessionActivity() {
        this.presenter = new CurrentSessionPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_current);
        ImageButton newPicture = (ImageButton) findViewById(R.id.observaitionPictureButton);
        newPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        ImageButton newVideo = (ImageButton) findViewById(R.id.observaitionVideoButton);
        newVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });
        ImageButton newAudio = (ImageButton) findViewById(R.id.observaitionAudioButton);
        newAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO AUDIO
            }
        });
        ImageButton newNoMedia = (ImageButton) findViewById(R.id.observaitionNoMediaButton);
        newNoMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO NOMEDIA
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = intent.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
        }else if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            Uri videoUri = intent.getData();
        }
    }


}
