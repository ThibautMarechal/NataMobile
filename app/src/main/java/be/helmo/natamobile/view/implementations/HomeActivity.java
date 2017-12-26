package be.helmo.natamobile.view.implementations;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import be.helmo.natamobile.R;
import be.helmo.natamobile.adapter.SessionListViewAdapter;
import be.helmo.natamobile.presenter.implementations.HomeController;
import be.helmo.natamobile.presenter.interfaces.IHomeController;
import be.helmo.natamobile.tools.ImageViewUrlBinder;
import be.helmo.natamobile.view.interfaces.IHomeView;

public class HomeActivity extends AbstractActivity implements IHomeView {
    private static final int REQUEST_ALL_PERMISSION = 42;
    private final IHomeController controller;
    public HomeActivity() {
        this.controller = new HomeController(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //ASK ALL PERMISSIONS
        if(permissionsNeeded()){
            askAllPermission();
        }
        //NEW SESSION
        Button startNewSessionButton = findViewById(R.id.startSessionButton);
        startNewSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewSession();
            }
        });
        //LIST SESSION
        SessionListViewAdapter adapter = new SessionListViewAdapter(this, controller.getSessions());
        ListView sessionListView = findViewById(R.id.homeSessionListView);
        sessionListView.setAdapter(adapter);

        //USERNAME
        TextView username = findViewById(R.id.homeUsername);
        username.setText(controller.getUsername());
        //EMAIL
        TextView email = findViewById(R.id.home_email);
        email.setText(controller.getUserEmail());
        //PICTURE
        final ImageView pp = findViewById(R.id.homeImageProfile);
        String ppUrl = controller.getUserPictureProfile();
        ImageViewUrlBinder.bind(pp, ppUrl);

    }

    private boolean permissionsNeeded() {
        boolean permissionGranted = true;
        for (int i = 0; i < permissions.length && permissionGranted; i++) {
            permissionGranted &= PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,permissions[i]);
        }
        return permissionGranted;
    }

    private void askAllPermission() {
        ActivityCompat.requestPermissions(this,
            permissions,
            REQUEST_ALL_PERMISSION);
    }
    private void startNewSession() {
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        controller.startNewSession(location.getLongitude(), location.getLatitude());
    }
}
