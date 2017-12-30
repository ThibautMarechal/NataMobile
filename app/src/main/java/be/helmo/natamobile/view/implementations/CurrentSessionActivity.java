package be.helmo.natamobile.view.implementations;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.helmo.natamobile.R;
import be.helmo.natamobile.adapter.ObservationListViewAdapter;
import be.helmo.natamobile.controller.implementations.CurrentSessionController;
import be.helmo.natamobile.controller.interfaces.ICurrentSessionController;
import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.models.User;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionActivity extends AbstractActivity implements ICurrentSessionView {
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int REQUEST_VIDEO_CAPTURE = 2;
	private static final int REQUEST_AUDIO_CAPTURE = 3;
	private static final int REQUEST_IDENTIFY = 4;
	private final ICurrentSessionController controller;
	private ImageButton newPictureButton;
	private ImageButton newVideoButton;
	private ImageButton newAudioButton;
	private ImageButton newNoMediaButton;
	private Button stopSession;
	private ListView observationListView;
	private String filePath;

	private StorageReference mStorageRef;

	private Session session;

	private Timestamp dateStart;

	public CurrentSessionActivity() {
		this.controller = new CurrentSessionController(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mStorageRef = FirebaseStorage.getInstance(be.helmo.natamobile.tools.Environment.FIREBASE_INSTANCE).getReference();

		setContentView(R.layout.session_current);
		//PICTURE
		newPictureButton = findViewById(R.id.observaitionPictureButton);
		newPictureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				takePicture();
			}
		});
		//VIDEO
		newVideoButton = findViewById(R.id.observaitionVideoButton);
		newVideoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				takeVideo();
			}
		});
		//AUDIO
		newAudioButton = findViewById(R.id.observaitionAudioButton);
		newAudioButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				takeAudio();
			}
		});
		//NO_MEDIA
		newNoMediaButton = findViewById(R.id.observaitionNoMediaButton);
		newNoMediaButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				controller.newObservationNoMedia();
			}
		});
		//OBSERVATION LIST
		observationListView = findViewById(R.id.currentSessionObservationList);
		ObservationListViewAdapter adapter = new ObservationListViewAdapter(this, controller.getObservations());
		observationListView.setAdapter(adapter);

		//STOP SESSION
		stopSession = findViewById(R.id.stopSessionButton);
		stopSession.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				stopSession();
			}
		});

		session = new Session();
		session.setLatitude(getSharedLatitude());
		session.setLongitude(getSharedLongitude());
		session.setStart(dateStart = new Timestamp(new Date().getTime()));
		User owner = new User();
		owner.setId(getSharedId());
		session.setUser(owner);
		session.setObservations(new ArrayList<Observation>());
	}

	private void takeAudio() {
		Intent intent = new Intent(this, AudioRecorderActivity.class);
		startActivityForResult(intent, REQUEST_AUDIO_CAPTURE);
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
				ex.printStackTrace();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				Uri photoURI = FileProvider.getUriForFile(this,
					  "be.helmo.android.fileprovider",
					  photoFile);
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
				this.filePath = photoURI.toString();
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Uri photoUri = intent.getData();
			if (photoUri != null) {
				this.filePath = photoUri.toString();
				upload(photoUri, "user-" + getSharedId()
					  + "/session-" + dateStart.getTime()
					  + "/photo-" + new Date().getTime() + ".jpg");
			}
		} else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
			Uri videoUri = intent.getData();
			if (videoUri != null) {
				this.filePath = videoUri.toString();

				//FORMAT "user-" + ID + "/session-" + TIMESTAMP + "/" + media_type + "-" + TIMESTAMP + "." + EXTENSION
				upload(videoUri, "user-" + getSharedId()
					  + "/session-" + dateStart.getTime()
					  + "/video-" + new Date().getTime() + ".mp4");

			}
		} else if (requestCode == REQUEST_AUDIO_CAPTURE && resultCode == RESULT_OK) {
			Uri audioUri = intent.getData();
			if (audioUri != null) {
				this.filePath = audioUri.toString();

				upload(audioUri, "user-" + getSharedId()
					  + "/session-" + dateStart.getTime()
					  + "/audio-" + new Date().getTime() + ".acc");
			}
		} else if (requestCode == REQUEST_IDENTIFY && resultCode == RESULT_OK) {
			controller.newObservationPicture(this.filePath);
			controller.newObservationVideo(this.filePath);
			controller.newObservationAudio(this.filePath);
			controller.newObservationNoMedia();
		}
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "PIC_" + timeStamp + "_";
		File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(
			  imageFileName,  /* prefix */
			  ".jpg",         /* suffix */
			  storageDir      /* directory */
		);
		// Save a file: path for use with ACTION_VIEW intents
		return image;
	}

	@SuppressLint("MissingPermission") // All permissions asked before
	private void upload(Uri uri, final String online) {
		StorageReference fileRef = mStorageRef.child(online);

		final Observation newObs = new Observation();
		newObs.setDate(new Timestamp(new Date().getTime()));
		newObs.setMediaPath(online);
		newObs.setNumberOfBird(1);

		FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
		mFusedLocationClient.getLastLocation()
			  .addOnSuccessListener(this, new OnSuccessListener<Location>() {
				  @Override
				  public void onSuccess(Location location) {
					  if (location != null) {
						  newObs.setLatitude(Double.toString(location.getLatitude()));
						  newObs.setLongitude(Double.toString(location.getLongitude()));
					  }
				  }
			  });

		fileRef.putFile(uri)
			  .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
				  @Override
				  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
					  // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
					  session.addObservation(newObs);
					  displayToast("Upload done");
				  }
			  })
			  .addOnFailureListener(new OnFailureListener() {
				  @Override
				  public void onFailure(@NonNull Exception exception) {
					  // Handle unsuccessful uploads
					  // ...
					  displayToast("Upload failure");
					  exception.printStackTrace();
					  System.err.println(exception.getMessage());
				  }
			  });
	}

	@Override
	public void updateObservationList() {
		ObservationListViewAdapter adapter = (ObservationListViewAdapter) observationListView.getAdapter();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void identifyBird(Observation obs) {
		Intent identifyIntent = new Intent(this, IdentifyActivity.class);
		identifyIntent.putExtra("filePath", obs.getFilePath());
		identifyIntent.putExtra("fileType", obs.getFileType());
		startActivityForResult(identifyIntent, REQUEST_IDENTIFY);
	}

	public void stopSession() {
		session.setEnd(new Timestamp(new Date().getTime()));
		session.setName("Test-" + new Timestamp(new Date().getTime()));
		controller.saveSession(session);
	}


}
