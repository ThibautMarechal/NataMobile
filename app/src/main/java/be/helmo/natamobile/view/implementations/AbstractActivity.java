package be.helmo.natamobile.view.implementations;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import be.helmo.natamobile.view.interfaces.IView;

/**
 * Created by marechthib on 19/12/2017.
 */

public abstract class AbstractActivity extends AppCompatActivity implements IView {
    protected String[] permissions= new String[]{
        Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET,
    };

    public void showView(ViewEnum view){
        Intent intent = new Intent(this, view.getActivityClass());
        startActivity(intent);
    }
    public void displayToast(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    protected void onCreate(Bundle savedInstanceState){
        //TODO AUTH HERE
        super.onCreate(savedInstanceState);
    }
}
