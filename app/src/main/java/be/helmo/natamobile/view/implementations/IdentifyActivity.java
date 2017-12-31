package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import be.helmo.natamobile.R;
import be.helmo.natamobile.controller.implementations.IdentifyController;
import be.helmo.natamobile.controller.interfaces.IIdentifyController;
import be.helmo.natamobile.models.FileType;
import be.helmo.natamobile.tools.Environment;
import be.helmo.natamobile.view.interfaces.IIdentifyView;

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
		controller = new IdentifyController(this);

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

		updateSpinnerBird();

		numberBirdEditText = findViewById(R.id.numberBirdTextField);
		numberBirdEditText.setText("1");
		saveButton = findViewById(R.id.identify_save_obs);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finishActivity(
					  controller.getBirdIdByName(birdsSpinner.getSelectedItem().toString()),
					  Integer.parseInt(numberBirdEditText.getText().toString()),
					  birdsSpinner.getSelectedItem().toString());
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

	@Override
	public void updateSpinnerBird() {
		birdsSpinner = findViewById(R.id.birdsSpinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, controller.getBirds());
		birdsSpinner.setAdapter(adapter);

	}

	private void askHelper() {
		Intent identifyHelperIntent = new Intent(this, IdentifyHelperActivity.class);
		startActivityForResult(identifyHelperIntent, REQUEST_HELPER);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		finishActivity(
			  data.getLongExtra("idBird", 0),
			  data.getIntExtra("nbrObs", 1),
			  data.getStringExtra("nameBird"));
	}

	private void finishActivity(Long id, int nbr, String nameBird) {
		Intent returnIntent = new Intent();
		returnIntent.putExtra("idBird", id);
		returnIntent.putExtra("nbrObs", nbr);
		returnIntent.putExtra("nameBird", nameBird);
		setResult(RESULT_OK, returnIntent);
		finish();
	}
}
