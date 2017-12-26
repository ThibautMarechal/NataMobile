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

import be.helmo.natamobile.R;
import be.helmo.natamobile.controller.implementations.IdentifyController;
import be.helmo.natamobile.controller.interfaces.IIdentifyController;
import be.helmo.natamobile.models.FileType;
import be.helmo.natamobile.view.interfaces.IIdentifyView;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public class IdentifyActivity extends AbstractActivity implements IIdentifyView{
    private Spinner birdsSpinner;
    private EditText numberBirdEditText;
    private Button saveButton;
    private Button identifyHelperButton;
    private ImageView birdImage;
    private IIdentifyController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify);

        birdImage = findViewById(R.id.identifyBirdImage);
        FileType fileType = (FileType) savedInstanceState.get("fileType");
        switch (fileType){
            case PICTURE:
                birdImage.setImageURI(Uri.parse(savedInstanceState.getString("filePath")));
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,controller.getBirds());
        birdsSpinner.setAdapter(adapter);

        numberBirdEditText = findViewById(R.id.numberBirdTextField);
        saveButton = findViewById(R.id.identify_save_obs);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("birdName", ""/*Spinner value*/);
                returnIntent.putExtra("birdNumber", Integer.parseInt(numberBirdEditText.toString()));
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        identifyHelperButton = findViewById(R.id.identifyHelperButton);
        identifyHelperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
