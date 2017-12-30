package be.helmo.natamobile.view.implementations;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.Timestamp;
import java.util.Date;

import be.helmo.natamobile.R;
import be.helmo.natamobile.controller.implementations.IdentifyController;
import be.helmo.natamobile.controller.interfaces.IIdentifyController;
import be.helmo.natamobile.models.FileType;
import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.tools.Environment;
import be.helmo.natamobile.view.interfaces.IIdentifyView;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public class IdentifyActivity extends AbstractActivity implements IIdentifyView {
	private static final int REQUEST_HELPER = 1;
	private Spinner birdsSpinner;
	private EditText numberBirdEditText;
	private Button saveButton;
	private Button identifyHelperButton;
	private ImageView birdImage;
	private IIdentifyController controller;

	private StorageReference mStorageRef;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.identify);

		mStorageRef = FirebaseStorage.getInstance(Environment.FIREBASE_INSTANCE).getReference();


		Bundle extras = getIntent().getExtras();
		birdImage = findViewById(R.id.identifyBirdImage);
		FileType fileType = (FileType) extras.get("fileType");
		switch (fileType) {
			case PICTURE:
				birdImage.setImageURI(Uri.parse(extras.getString("filePath")));
				break;
			case AUDIO:
				birdImage.setImageResource(R.drawable.audio);
				break;
			case VIDEO:
				birdImage.setImageResource(R.drawable.video);
				break;
			default:
				birdImage.setImageResource(R.drawable.no_media);
				break;
		}
		birdsSpinner = findViewById(R.id.birdsSpinner);
		controller = new IdentifyController();
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, controller.getBirds());
		birdsSpinner.setAdapter(adapter);

		numberBirdEditText = findViewById(R.id.numberBirdTextField);
		numberBirdEditText.setText("1");
		saveButton = findViewById(R.id.identify_save_obs);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				returnIntent.putExtra("birdName", birdsSpinner.getSelectedItem().toString());
				returnIntent.putExtra("birdNumber", Integer.parseInt(numberBirdEditText.getText().toString()));
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
		identifyHelperButton = findViewById(R.id.identifyHelperButton);
		identifyHelperButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				askHelper();
			}
		});
	}

	private void askHelper() {
		Intent identifyHelperIntent = new Intent(this, IdentifyHelperActivity.class);
		startActivityForResult(identifyHelperIntent, REQUEST_HELPER);
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
//					  session.addObservation(newObs);
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
}
